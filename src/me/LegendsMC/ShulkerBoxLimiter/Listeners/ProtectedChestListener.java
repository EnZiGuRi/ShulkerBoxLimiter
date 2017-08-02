package me.LegendsMC.ShulkerBoxLimiter.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import com.griefcraft.lwc.LWC;
import com.griefcraft.model.Protection;

import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;

public class ProtectedChestListener implements Listener {
	
	LWC lwc = com.griefcraft.lwc.LWC.getInstance();
	
	Protection isProtected;
	
	@EventHandler(priority = EventPriority.HIGH)
    public void onClickedChest(PlayerInteractEvent event) {
		Action action = event.getAction();
		Block chest;
		if(event.getClickedBlock().getType() == Material.CHEST ){
			if(action.equals(Action.RIGHT_CLICK_BLOCK) || (action.equals(Action.RIGHT_CLICK_BLOCK))){
				chest = event.getClickedBlock();
				isProtected = lwc.findProtection(chest);
			}
		}
    }
	
    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Inventory top = event.getView().getTopInventory();
		Player player = (Player) event.getWhoClicked();
        Inventory bottom = event.getView().getBottomInventory();
        ItemStack clickeditem = event.getCurrentItem();
        BlockStateMeta clickeditemmeta = (BlockStateMeta)clickeditem.getItemMeta();
        if(clickeditem.getItemMeta() instanceof BlockStateMeta){
            if(clickeditemmeta.getBlockState() instanceof ShulkerBox){
	            if(top.getType() == InventoryType.CHEST && bottom.getType() == InventoryType.PLAYER){
	                if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                    	int inventorySize = event.getInventory().getSize();
                        if(event.getRawSlot() > inventorySize) {
                        	if(isProtected != null){
	                            event.setCancelled(true);
	        	    			player.sendMessage(ChatColor.DARK_GREEN
	        	    					+ "[ShulkerBoxLimiter]: "
	        	    					+ ChatColor.WHITE
	        	    					+ "You can´t move ShulkerBox to a LWC protected Chest!");
                        	}
                        }
	                }
	            }
	        }
        }
    }
}
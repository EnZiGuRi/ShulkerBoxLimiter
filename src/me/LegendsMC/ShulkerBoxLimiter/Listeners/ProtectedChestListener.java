package me.LegendsMC.ShulkerBoxLimiter.Listeners;

import java.util.Set;

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
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;

import com.griefcraft.lwc.LWC;
import com.griefcraft.scripting.event.LWCBlockInteractEvent;
import com.griefcraft.model.Protection;

import me.LegendsMC.ShulkerBoxLimiter.ShulkerBoxLimiter;

public class ProtectedChestListener implements Listener{

	LWC lwc = ShulkerBoxLimiter.lwc;
	Protection isProtected;
	Player player1;
	Player player2;
	
    /*public void onClickedChest(LWCProtectionInteractEvent event) {
		player1 = event.getPlayer();
		player2.sendMessage(ChatColor.DARK_GREEN
				+ "[ShulkerBoxLimiter]: "
				+ ChatColor.WHITE
				+ "LWCProtectionInteractEvent - " + player1);
	}*/
	
	@EventHandler(priority = EventPriority.HIGH)
    public void onClickedChest(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		player2 = event.getPlayer();
		Action action = event.getAction();
		Block chest;
		Set<String> actions = null;
		if((action.equals(Action.LEFT_CLICK_BLOCK) || (action.equals(Action.RIGHT_CLICK_BLOCK))) 
		&& (!action.equals(Action.LEFT_CLICK_AIR) || (!action.equals(Action.RIGHT_CLICK_AIR)))){
			if(event.getClickedBlock().getType() == Material.CHEST){
				chest = event.getClickedBlock();
				LWCBlockInteractEvent lwcevent = new LWCBlockInteractEvent(event, chest, actions);
				isProtected = lwc.findProtection(chest);
				actions = lwcevent.getActions();
				player.sendMessage(ChatColor.DARK_GREEN
						+ "[ShulkerBoxLimiter]: "
						+ ChatColor.WHITE
						+ "Action - " + actions);
				if (isProtected == null){
					player.sendMessage(ChatColor.DARK_GREEN
							+ "[ShulkerBoxLimiter]: "
							+ ChatColor.WHITE
							+ "Not Protected! " + actions);
				}
				if (isProtected != null){
					player.sendMessage(ChatColor.DARK_GREEN
							+ "[ShulkerBoxLimiter]: "
							+ ChatColor.WHITE
							+ "Protected! " + actions);
				}
			}
		}
    }
	
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory top = event.getView().getTopInventory();
		Player player = (Player) event.getWhoClicked();
        Inventory bottom = event.getView().getBottomInventory();
        ItemStack clickeditem = event.getCurrentItem();
        if((clickeditem != null) && (clickeditem.getItemMeta() instanceof BlockStateMeta)){
            BlockStateMeta clickeditemmeta = (BlockStateMeta)clickeditem.getItemMeta();
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
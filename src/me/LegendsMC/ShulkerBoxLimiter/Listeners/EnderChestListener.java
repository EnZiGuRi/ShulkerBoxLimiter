package me.LegendsMC.ShulkerBoxLimiter.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.block.ShulkerBox;

public class EnderChestListener implements Listener {
    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Inventory top = event.getView().getTopInventory();
		Player player = (Player) event.getWhoClicked();
        Inventory bottom = event.getView().getBottomInventory();
        ItemStack clickeditem = event.getCurrentItem();
        if((clickeditem != null) && (clickeditem.getItemMeta() instanceof BlockStateMeta)){
            BlockStateMeta clickeditemmeta = (BlockStateMeta)clickeditem.getItemMeta();
            if(clickeditemmeta.getBlockState() instanceof ShulkerBox){
	            if(top.getType() == InventoryType.ENDER_CHEST && bottom.getType() == InventoryType.PLAYER){
	                if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                    	int inventorySize = event.getInventory().getSize();
                        if(event.getRawSlot() > inventorySize) {
                            event.setCancelled(true);
        	    			player.sendMessage(ChatColor.DARK_GREEN
        	    					+ "[ShulkerBoxLimiter]: "
        	    					+ ChatColor.WHITE
        	    					+ "You can�t move ShulkerBox to Ender Chest!");
                        }
	                }
	            }
            }
        }
    }
}
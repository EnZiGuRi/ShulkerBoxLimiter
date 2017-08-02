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

public class BackpackListener implements Listener {
	@EventHandler
    public void onInv(InventoryClickEvent event) {
        Inventory top = event.getView().getTopInventory();
		Player player = (Player) event.getWhoClicked();
        Inventory bottom = event.getView().getBottomInventory();
        ItemStack clickeditem = event.getCurrentItem();
        BlockStateMeta clickeditemmeta = (BlockStateMeta)clickeditem.getItemMeta();
        if(clickeditem.getItemMeta() instanceof BlockStateMeta){
            if(clickeditemmeta.getBlockState() instanceof ShulkerBox){
	            if(top.getName().contains("Backpack") && bottom.getType() == InventoryType.PLAYER){
	                if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                    	int inventorySize = event.getInventory().getSize();
                        if(event.getRawSlot() > inventorySize) {
                            event.setCancelled(true);
        	    			player.sendMessage(ChatColor.DARK_GREEN
        	    					+ "[ShulkerBoxLimiter]: "
        	    					+ ChatColor.WHITE
        	    					+ "You can´t move ShulkerBox to your Backpack!");
                        }
	                }
	            }
            }
        }
    }
	
	
	
	
	
	/*
    @EventHandler
    public void onInv(InventoryClickEvent event) {
        Inventory top = event.getView().getTopInventory();
		Player player = (Player) event.getWhoClicked();
        Inventory bottom = event.getView().getBottomInventory();
    	ItemStack cursoritem = event.getCursor();
        BlockStateMeta cursoritemmeta = (BlockStateMeta)cursoritem.getItemMeta();
        //ItemStack clickeditem = event.getCurrentItem();
        //BlockStateMeta clickeditemmeta = (BlockStateMeta)clickeditem.getItemMeta();
        //if(clickeditem.getItemMeta() instanceof BlockStateMeta){
            //if(clickeditemmeta.getBlockState() instanceof ShulkerBox){
	            if(top.getName().contains("Backpack") && bottom.getType() == InventoryType.PLAYER){
	                //if(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
                    	int inventorySize = event.getInventory().getSize();
                        if(event.getRawSlot() > inventorySize) {
        	    			player.sendMessage(ChatColor.DARK_GREEN
        	    					+ "[ShulkerBoxLimiter]: "
        	    					+ ChatColor.WHITE
        	    					+ "You can move ShulkerBox on this inventory" + event.getCursor());
                        }else if(event.getRawSlot() < inventorySize) {
                            if(cursoritem.getItemMeta() instanceof BlockStateMeta){
                                if(cursoritemmeta.getBlockState() instanceof ShulkerBox){
		                            event.setCancelled(true);
		        	    			player.sendMessage(ChatColor.DARK_GREEN
		        	    					+ "[ShulkerBoxLimiter]: "
		        	    					+ ChatColor.WHITE
		        	    					+ "You can't move ShulkerBox on this inventory");
                                }
                            //}
                        //}
	                ///
	            }
            }
        }
    }*/
}
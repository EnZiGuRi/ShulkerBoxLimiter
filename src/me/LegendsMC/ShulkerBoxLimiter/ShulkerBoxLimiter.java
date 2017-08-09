package me.LegendsMC.ShulkerBoxLimiter;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;

import me.LegendsMC.ShulkerBoxLimiter.Listeners.BackpackListener;
import me.LegendsMC.ShulkerBoxLimiter.Listeners.EnderChestListener;
import me.LegendsMC.ShulkerBoxLimiter.Listeners.ProtectedChestListener;

public class ShulkerBoxLimiter extends JavaPlugin {
	private static Plugin instance;
	public static LWC lwc = null;

	boolean DebugMode = getConfig().getBoolean("DebugMode");

	public void onEnable() {
		instance = this;
		Plugin lwcPlugin = getServer().getPluginManager().getPlugin("LWC");
        if(lwcPlugin != null) {
            lwc = ((LWCPlugin) lwcPlugin).getLWC();
        }
		
		initializeConfig();
		registerEvents();
		registerCommands();
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GREEN + "[ShulkerBoxLimiter] Enabled!");
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.GREEN + "[ShulkerBoxLimiter] Disabled!");
	}

	public void initializeConfig() {
		File configfile = new File(getDataFolder() + File.separator
				+ "config.yml");
		if (!configfile.exists()) {
			Bukkit.getConsoleSender().sendMessage(
					ChatColor.GREEN
							+ "[ShulkerBoxLimiter] Generating config files...");
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}

	public void registerEvent(Listener listener) {
		getServer().getPluginManager().registerEvents(listener, this);
	}

	private void registerEvents() {
		registerEvent(new BackpackListener());
		registerEvent(new EnderChestListener());
		registerEvent(new ProtectedChestListener());
	}

	private void registerCommands() {
		
	}

	public static Plugin getInstance() {
		return instance;
	}
}
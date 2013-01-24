package net.catharos.adventures;

import org.bukkit.plugin.java.JavaPlugin;

public class cAdventures extends JavaPlugin {

	/** Enables the plugin */
	public void onEnable() {
		// Store default configuration
		getConfig().options().copyDefaults( true );
		this.saveConfig();
	}

	/** Disables the plugin */
	public void onDisable() {
	}

}

package net.catharos.adventures;

import net.catharos.adventures.events.listener.cTagListener;
import org.bukkit.plugin.java.JavaPlugin;

public class cAdventures extends JavaPlugin {

	/** Enables the plugin */
	public void onEnable() {
		// Store default configuration
		getConfig().options().copyDefaults( true );
		this.saveConfig();
		
		new cTagListener(this);
	}

	/** Disables the plugin */
	public void onDisable() {
	}

}

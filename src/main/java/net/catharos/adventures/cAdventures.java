package net.catharos.adventures;

import net.catharos.adventures.packets.TagHelper;
import org.bukkit.plugin.java.JavaPlugin;

public class cAdventures extends JavaPlugin {
	private TagHelper tagHelper;
	
	/** Enables the plugin */
	@Override
	public void onEnable() {
		// Store default configuration
		getConfig().options().copyDefaults( true );
		this.saveConfig();
		
		tagHelper = new TagHelper(this);
	}


}

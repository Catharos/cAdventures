package net.catharos.adventures.packets;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class TagHelper {
	
	private ProtocolManager manager;
	private Logger logger;
	
	private final TagPacketHandler tagHandler;
	
	public TagHelper(JavaPlugin plugin) {
		manager = ProtocolLibrary.getProtocolManager();
		logger = plugin.getLogger();
		
		tagHandler = new TagPacketHandler(plugin, plugin.getServer().getPluginManager(), logger);
		manager.addPacketListener(getHandler());
	}
	
	public final TagPacketHandler getHandler() {
		 return tagHandler;
	}
	
}

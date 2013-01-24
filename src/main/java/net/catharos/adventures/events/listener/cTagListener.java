package net.catharos.adventures.events.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class cTagListener implements Listener {
    public cTagListener( JavaPlugin plugin ) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onNameTag(PlayerRecieveNameTagEvent event) {
        
    }
}

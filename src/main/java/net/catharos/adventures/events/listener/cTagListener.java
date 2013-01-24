package net.catharos.adventures.events.listener;

import net.aufdemrand.denizen.npc.traits.AssignmentTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

public class cTagListener implements Listener {
    public cTagListener( JavaPlugin plugin ) {
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onNameTag( PlayerReceiveNameTagEvent event ) {
        Player recieved = event.getPlayer();
        Player named = event.getNamedPlayer();

        NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
        
	for(NPC npc : npcRegistry) {
	    if( !npc.hasTrait(AssignmentTrait.class) || !npc.getTrait(AssignmentTrait.class).hasAssignment() ) {
		continue;
	    }
	    
	    if(npc.getBukkitEntity().getEntityId() == named.getEntityId()) {
		String tag = event.getTag();
		event.setTag(ChatColor.GOLD + tag);
	    }
        }
    }
}

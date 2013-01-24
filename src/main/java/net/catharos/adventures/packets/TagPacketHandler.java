package net.catharos.adventures.packets;

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;
import com.comphenix.protocol.reflect.StructureModifier;
import java.util.logging.Logger;
import net.aufdemrand.denizen.npc.traits.AssignmentTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TagPacketHandler extends PacketAdapter {
	
	private Logger logger;
	private PluginManager pluginManager;
	
	public TagPacketHandler(JavaPlugin plugin, PluginManager pluginManager, Logger logger) {
		super(plugin, ConnectionSide.SERVER_SIDE, Packets.Server.NAMED_ENTITY_SPAWN);
		
		this.pluginManager = pluginManager;
		this.logger = logger;
	}
	
	@Override
	public void onPacketSending(PacketEvent event) {
		if (event.isCancelled() || event.getPacketID() != Packets.Server.NAMED_ENTITY_SPAWN) return;
		
		PacketContainer packet = event.getPacket();
		StructureModifier<String> text = packet.getSpecificModifier(String.class);
		
		try {
			String tag = text.read(0);
			Player observer = event.getPlayer();
			Entity watched = packet.getEntityModifier(observer.getWorld()).read(0);
			
			if(tag != null && isDenizen(watched.getEntityId())) {
				tag = ChatColor.GOLD + tag;
				
				if(tag.length() > 16)
					tag = tag.substring(0, 16);
				
				text.write(0, tag);
			}
		} catch(FieldAccessException e) {}
	}
	
	private boolean isDenizen(int entityId) {
		NPCRegistry npcRegistry = CitizensAPI.getNPCRegistry();
		
		for(NPC npc : npcRegistry) {
			if( !npc.hasTrait(AssignmentTrait.class) || !npc.getTrait(AssignmentTrait.class).hasAssignment() ) {
				continue;
			}

			if(npc.getBukkitEntity().getEntityId() == entityId) {
				return true;
			}
		}
		
		return false;
	}
}

package com.battlearena.listeners.entity.player;

import com.battlearena.BattleArena;
import com.battlearena.listeners.BAListener;
import com.battlearena.utils.ChatUtilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChat extends BAListener{
    
    public OnChat(BattleArena pl){
        
        super(pl);
        
    }
    
    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent pc) {

        pc.setCancelled(true);
        ChatUtilities.chat(pc.getMessage(), pc.getPlayer());
        
    }
    
}

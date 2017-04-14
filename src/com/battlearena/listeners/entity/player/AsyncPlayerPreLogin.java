package com.battlearena.listeners.entity.player;

import com.battlearena.GameState;
import com.battlearena.BattleArena;
import com.battlearena.listeners.BAListener;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

public class AsyncPlayerPreLogin extends BAListener {

    public AsyncPlayerPreLogin(BattleArena pl) {
        super(pl);
    }

    @EventHandler
    public void playerPreLogin(AsyncPlayerPreLoginEvent e) {

        if (GameState.isJoinable()) {

            e.allow();

        } else {

            e.disallow(Result.KICK_OTHER, ChatColor.RED + "Game is in progress! Try joining later!");
            
        }

    }
}
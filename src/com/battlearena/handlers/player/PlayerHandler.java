package com.battlearena.handlers.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.omg.PortableInterceptor.INACTIVE;

public class PlayerHandler {

    private static HashMap<UUID, Integer> playerGold = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> playerCS = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> playerKills = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> playerDeaths = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Loadout> playerLoadouts = new HashMap<UUID, Loadout>();

    public static void initialisePlayerGold(Player p){

        playerGold.put(p.getUniqueId(), 0);

    }

    public static int getPlayerGold(Player p){

        return playerGold.get(p.getUniqueId());

    }

    public static void setPlayerGold(Player p, int gold){

        playerGold.put(p.getUniqueId(), gold);

    }

    public static void initialisePlayerCS(Player p){

        playerCS.put(p.getUniqueId(), 0);

    }

    public static int getPlayerCS(Player p) {

        return playerCS.get(p.getUniqueId());

    }

    public static void setPlayerCS(Player p, int cs){

        playerCS.put(p.getUniqueId(), cs);

    }

    public static void initialisePlayerKills(Player p){

        playerKills.put(p.getUniqueId(), 0);

    }

    public static int getPlayerKills(Player p) {

        return playerKills.get(p.getUniqueId());

    }

    public static void setPlayerKills(Player p, int kills){

        playerKills.put(p.getUniqueId(), kills);

    }

    public static void initialisePlayerDeaths(Player p){

        playerDeaths.put(p.getUniqueId(), 0);

    }

    public static int getPlayerDeaths(Player p) {

        return playerDeaths.get(p.getUniqueId());

    }

    public static void setPlayerDeaths(Player p, int kills){

        playerDeaths.put(p.getUniqueId(), kills);

    }

    public static void setPlayerLoadout(Player p, Loadout loadout){

        playerLoadouts.put(p.getUniqueId(), loadout);

    }

    public static Loadout getPlayerLoadout(Player p){

        return playerLoadouts.get(p.getUniqueId());

    }

}

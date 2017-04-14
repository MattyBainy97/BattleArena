package com.battlearena.handlers.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Team {

    private static HashMap<UUID, TeamName> playerTeams = new HashMap<UUID, TeamName>();

    public enum TeamName {

        RED, BLUE, SPEC;

    }

    public static TeamName getPlayerTeam(Player p){

        return playerTeams.get(p.getUniqueId());

    }

    public static void setPlayerTeam(Player p, TeamName team){

        playerTeams.put(p.getUniqueId(), team);

    }

    public static void removePlayerTeam(Player p){

        playerTeams.remove(p.getUniqueId());

    }

}

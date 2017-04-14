package com.battlearena.utils;

import com.battlearena.handlers.player.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardUtilities {

    private static HashMap<UUID, Scoreboard> scoreboardList = new HashMap<UUID, Scoreboard>();

    public static void initialisePlayerScoreboard(Player p) {

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "     BattleArena     ");

        Score empty = objective.getScore("");
        empty.setScore(16);
        Score nameTitle = objective.getScore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Loadout");
        nameTitle.setScore(15);
        Score name = objective.getScore(ChatColor.WHITE + PlayerHandler.getPlayerLoadout(p).getName());
        name.setScore(14);
        Score empty2 = objective.getScore(" ");
        empty2.setScore(13);
        Score timerTitle = objective.getScore(ChatColor.AQUA + "" + ChatColor.BOLD + "Timer");
        timerTitle.setScore(12);
        Score timer = objective.getScore(ChatColor.WHITE + "00:00");
        timer.setScore(11);
        Score empty3 = objective.getScore("  ");
        empty3.setScore(10);
        Score kdTitle = objective.getScore(ChatColor.RED + "" + ChatColor.BOLD + "KD");
        kdTitle.setScore(9);
        Score kd = objective.getScore(ChatColor.WHITE + "0/0");
        kd.setScore(8);
        Score empty4 = objective.getScore( "   ");
        empty4.setScore(7);
        Score csTitle = objective.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Creep Score");
        csTitle.setScore(6);
        Score cs = objective.getScore(ChatColor.RED + "" + ChatColor.WHITE + "0");
        cs.setScore(5);
        Score empty5 = objective.getScore( "    ");
        empty5.setScore(4);
        Score goldTitle = objective.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "Gold");
        goldTitle.setScore(3);
        Score goldVal = objective.getScore(ChatColor.WHITE + Integer.toString(PlayerHandler.getPlayerGold(p)));
        goldVal.setScore(2);
        Score empty6 = objective.getScore("     ");
        empty6.setScore(1);

        scoreboardList.put(p.getUniqueId(), scoreboard);
        p.setScoreboard(scoreboard);

    }

    public static void setGold(Player p, int gold){

        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        board.resetScores(ChatColor.WHITE + Integer.toString(PlayerHandler.getPlayerGold(p)));
        Score goldVal = objective.getScore(ChatColor.WHITE + Integer.toString(gold));
        goldVal.setScore(2);
        PlayerHandler.setPlayerGold(p, gold);

    }

    public static void setTime(Player p, int newTime){

        int currentTime = newTime - 1;

        String currentTimeString = String.format("%02d:%02d", currentTime / 60, currentTime % 60);

        String newTimeString = String.format("%02d:%02d", newTime / 60, newTime % 60);

        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        board.resetScores(ChatColor.WHITE + currentTimeString);
        Score timer = objective.getScore(ChatColor.WHITE + newTimeString);
        timer.setScore(11);

    }

    public static void setCS(Player p, int crs){

        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        board.resetScores(ChatColor.RED + "" + ChatColor.WHITE + Integer.toString(PlayerHandler.getPlayerCS(p)));
        Score cs = objective.getScore(ChatColor.RED + "" + ChatColor.WHITE + Integer.toString(crs));
        cs.setScore(5);
        PlayerHandler.setPlayerCS(p, crs);

    }

    public static void updateKD(Player p, int kills, int deaths){

        int currentKills = PlayerHandler.getPlayerKills(p);
        int currentDeaths = PlayerHandler.getPlayerDeaths(p);
        String newKD = kills + "/" + deaths;
        String currentKD = currentKills + "/" + currentDeaths;
        Scoreboard board = p.getScoreboard();
        Objective objective = board.getObjective(DisplaySlot.SIDEBAR);
        board.resetScores(ChatColor.WHITE + currentKD);
        Score kd = objective.getScore(ChatColor.WHITE + newKD);
        kd.setScore(8);
        PlayerHandler.setPlayerKills(p, kills);
        PlayerHandler.setPlayerDeaths(p, deaths);

    }

}

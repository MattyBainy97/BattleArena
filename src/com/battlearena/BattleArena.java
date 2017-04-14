package com.battlearena;

import com.battlearena.handlers.entity.CustomEntityType;
import com.battlearena.handlers.entity.shopkeeper.BlueShopkeeper;
import com.battlearena.handlers.entity.shopkeeper.RedShopkeeper;
import com.battlearena.handlers.game.Wave;
import com.battlearena.handlers.player.Loadout;
import com.battlearena.handlers.player.PlayerHandler;
import com.battlearena.listeners.entity.EntityDamageByEntity;
import com.battlearena.listeners.entity.EntityDeath;
import com.battlearena.listeners.entity.player.AsyncPlayerPreLogin;
import com.battlearena.listeners.entity.player.OnChat;
import com.battlearena.listeners.entity.player.PlayerJoin;
import com.battlearena.threads.GameTimer;
import com.battlearena.utils.ChatUtilities;
import com.battlearena.utils.ScoreboardUtilities;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BattleArena extends JavaPlugin {

    private static Plugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        GameState.setState(GameState.IN_LOBBY);

        CustomEntityType.registerEntities();

        Bukkit.setDefaultGameMode(GameMode.ADVENTURE);
        World w = Bukkit.getServer().getWorld("BattleArena");
        w.setAutoSave(false);
        w.setTime(0);
        w.setStorm(false);
        w.setWeatherDuration(9999999);
        w.setDifficulty(Difficulty.EASY);
        w.setGameRuleValue("doDaylightCycle", "false");
        for (Entity e : w.getEntities()) {

            e.remove();

        }

        registerListeners();

    }

    @Override
    public void onDisable() {

        GameState.setState(GameState.IN_LOBBY);

        for (Player p : Bukkit.getOnlinePlayers()) {

            p.kickPlayer("Rejoin.");

        }

        Bukkit.getScheduler().cancelTasks(plugin);

        GameTimer.stopTimer();

        CustomEntityType.unregisterEntities();

        plugin = null;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if(commandLabel.equalsIgnoreCase("start")) {

            GameState.setState(GameState.IN_GAME);

            Location redspawn = new Location(Bukkit.getWorld("BattleArena"), 649.5, 4, -35.5, 0, 0);
            Location bluespawn = new Location(Bukkit.getWorld("BattleArena"), 649.5, 4, 2.5, 180, 0);

            Location redShopSpawn = new Location(Bukkit.getWorld("BattleArena"), 643.5, 4, -37.5);
            Location blueShopSpawn = new Location(Bukkit.getWorld("BattleArena"), 655.5, 4, 4.5);

            Location bossSpawn = new Location(Bukkit.getWorld("BattleArena"), 668.5, 4, -16.5);

            RedShopkeeper red = new RedShopkeeper(((CraftWorld) redShopSpawn.getWorld()).getHandle());
            red.setPositionRotation(redShopSpawn.getX(), redShopSpawn.getY(), redShopSpawn.getZ(), redShopSpawn.getYaw(), redShopSpawn.getPitch());

            BlueShopkeeper blue = new BlueShopkeeper(((CraftWorld) blueShopSpawn.getWorld()).getHandle());
            blue.setPositionRotation(blueShopSpawn.getX(), blueShopSpawn.getY(), blueShopSpawn.getZ(), blueShopSpawn.getYaw(), blueShopSpawn.getPitch());

            ((CraftWorld) redShopSpawn.getWorld()).getHandle().addEntity(red);
            ((CraftWorld) blueShopSpawn.getWorld()).getHandle().addEntity(blue);

            ChatUtilities.playTitle((Player) sender, ChatColor.GOLD + "GAME START", 10, 40, 10);
            ChatUtilities.onePlayer("Test commenced", (Player) sender);

            for(Player p : Bukkit.getOnlinePlayers()){

                PlayerHandler.initialisePlayerGold(p);
                PlayerHandler.initialisePlayerCS(p);
                PlayerHandler.initialisePlayerKills(p);
                PlayerHandler.initialisePlayerDeaths(p);
                PlayerHandler.setPlayerLoadout(p, Loadout.WARRIOR);
                ScoreboardUtilities.initialisePlayerScoreboard(p);

            }

            new Wave(3, redspawn, bluespawn).runTaskTimer(this, 0, 20);

            new Thread(new GameTimer()).start();

        }

        if(commandLabel.equalsIgnoreCase("test")){

            ChatUtilities.broadcastServer("test");

        }

        return false;

    }

    public void registerListeners() {

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new AsyncPlayerPreLogin(this), this);
        pm.registerEvents(new OnChat(this), this);
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new EntityDamageByEntity(this), this);
        pm.registerEvents(new EntityDeath(this), this);

    }

    public static Plugin getPlugin(){

        return plugin;

    }

}

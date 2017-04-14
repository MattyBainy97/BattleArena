package com.battlearena.threads;

import com.battlearena.GameState;
import com.battlearena.handlers.player.PlayerHandler;
import com.battlearena.utils.ScoreboardUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameTimer implements Runnable {

    private static boolean stopped = false;
    private static int time;

    @Override
    public void run() {
        while (true) {
            if (GameState.isState(GameState.IN_GAME)) {
                for (; time >= 0; time++) {

                    if(stopped == true){

                        break;

                    }

                    for(Player p : Bukkit.getOnlinePlayers()){

                        ScoreboardUtilities.setGold(p, (PlayerHandler.getPlayerGold(p) + 2));
                        ScoreboardUtilities.setTime(p, time);

                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                }

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    public static void stopTimer(){

        stopped = true;

    }

}

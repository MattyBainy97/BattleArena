package com.battlearena.handlers.game;

import com.battlearena.handlers.entity.zombie.BlueMeleeZombie;
import com.battlearena.handlers.entity.zombie.RedMeleeZombie;
import com.battlearena.utils.ChatUtilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Wave extends BukkitRunnable {

    private int waveSize;
    private int waveCount;
    private Location redspawn;
    private Location bluespawn;

    public Wave(int waveSize, Location redspawn, Location bluespawn){

        this.waveSize = waveSize;
        this.redspawn = redspawn;
        this. bluespawn = bluespawn;

    }

    @Override
    public void run() {

        if (waveCount < waveSize) {

            waveCount++;

            RedMeleeZombie red = new RedMeleeZombie(((CraftWorld) redspawn.getWorld()).getHandle());
            red.setPositionRotation(redspawn.getX(), redspawn.getY(), redspawn.getZ(), redspawn.getYaw(), redspawn.getPitch());

            BlueMeleeZombie blue = new BlueMeleeZombie(((CraftWorld) bluespawn.getWorld()).getHandle());
            blue.setPositionRotation(bluespawn.getX(), bluespawn.getY(), bluespawn.getZ(), bluespawn.getYaw(), bluespawn.getPitch());

            ((CraftWorld) redspawn.getWorld()).getHandle().addEntity(red);
            ((CraftWorld) bluespawn.getWorld()).getHandle().addEntity(blue);

            try{

                Thread.sleep(500);

            }catch(InterruptedException ex){

                ex.printStackTrace();

            }

        }else {

            this.cancel();

        }

    }

}

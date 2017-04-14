package com.battlearena.listeners.entity;

import com.battlearena.BattleArena;
import com.battlearena.handlers.entity.zombie.BlueMeleeZombie;
import com.battlearena.handlers.entity.zombie.RedMeleeZombie;
import com.battlearena.handlers.player.PlayerHandler;
import com.battlearena.listeners.BAListener;
import com.battlearena.utils.ScoreboardUtilities;
import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EntityDeath extends BAListener {

    public EntityDeath(BattleArena pl){

        super(pl);

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){

        e.getDrops().clear();
        e.setDroppedExp(0);

        Entity killed = e.getEntity();
        net.minecraft.server.v1_11_R1.Entity nmsKilled = ((CraftEntity) killed).getHandle();
        Entity killer = e.getEntity().getKiller();

        if(nmsKilled instanceof BlueMeleeZombie){

            BlueMeleeZombie bms = (BlueMeleeZombie) nmsKilled;
            net.minecraft.server.v1_11_R1.Entity nmsKiller = bms.getLastDamager();

            if(nmsKiller instanceof EntityPlayer){

                grantGold(killer, killed);

            }else if(nmsKiller instanceof RedMeleeZombie){

                RedMeleeZombie rms = (RedMeleeZombie) nmsKiller;
                rms.setGoalTarget(null);

            }else{

                //todo for other entities

            }

        }else if(nmsKilled instanceof RedMeleeZombie){

            RedMeleeZombie rms = (RedMeleeZombie) nmsKilled;
            net.minecraft.server.v1_11_R1.Entity nmsKiller = rms.getLastDamager();

            if(nmsKiller instanceof EntityPlayer){

                grantGold(killer, killed);

            }else if(nmsKiller instanceof BlueMeleeZombie){

                BlueMeleeZombie bms = (BlueMeleeZombie) nmsKiller;
                bms.setGoalTarget(null);

            }else{

                //todo for other entities

            }

        }else{

            //todo for mobs later on

        }

    }

    private void grantGold(Entity killer, Entity killed){

        Random rand = new Random();
        int gold = rand.nextInt((25 - 18) + 1) + 18;

        Player p = (Player) killer;
        Location deathLocation = killed.getLocation();
        killed.remove();
        EntityArmorStand as = new EntityArmorStand(((CraftWorld)killer.getWorld()).getHandle());
        //ArmorStand as = deathLocation.getWorld().spawn(deathLocation, ArmorStand.class);
        as.setLocation(deathLocation.getX(), deathLocation.getY() + 1, deathLocation.getZ(), 0, 0);
        as.setSmall(true);
        as.setInvisible(true);
        as.setCustomName(ChatColor.BOLD.GOLD + "+" + gold);
        as.setCustomNameVisible(true);
        as.setMarker(true);
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(as);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        ScoreboardUtilities.setGold(p, (PlayerHandler.getPlayerGold(p) + gold));
        ScoreboardUtilities.setCS(p, (PlayerHandler.getPlayerCS(p) + 1));

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(BattleArena.getPlugin(), new Runnable() {
            @Override
            public void run() {

                PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(as.getId());
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(destroy);

            }
        }, 40L);

    }

}

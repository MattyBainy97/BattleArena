package com.battlearena.listeners.entity;

import com.battlearena.BattleArena;
import com.battlearena.GameState;
import com.battlearena.handlers.entity.zombie.BlueMeleeZombie;
import com.battlearena.handlers.entity.zombie.RedMeleeZombie;
import com.battlearena.listeners.BAListener;
import com.battlearena.utils.ChatUtilities;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity extends BAListener {

    public static Player playerHit;

    public EntityDamageByEntity(BattleArena pl) {
        super(pl);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (GameState.isState(GameState.IN_GAME)) {

            Entity damaged = event.getEntity();
            Entity damageEntity = event.getDamager();

            net.minecraft.server.v1_11_R1.Entity nmsDamaged = ((CraftEntity) damaged).getHandle();
            net.minecraft.server.v1_11_R1.Entity nmsDamager = ((CraftEntity) damageEntity).getHandle();


            if (damaged instanceof Player) {

                if (damageEntity instanceof Player) {

                    Player p = (Player) damageEntity;

                } else {

                    event.setCancelled(true);

                }

            } else if(nmsDamaged instanceof BlueMeleeZombie || nmsDamaged instanceof RedMeleeZombie){

                createBlood(damaged);

                if (nmsDamaged instanceof BlueMeleeZombie) {

                    BlueMeleeZombie blue = (BlueMeleeZombie) nmsDamaged;

                    if(nmsDamager instanceof EntityPlayer){

                        event.setDamage(0D);
                        blue.setHealth(blue.getHealth() - 2);

                    }else if(damageEntity instanceof Arrow){

                        double damage = event.getDamage();

                        if(damage >= 0 && damage <= 3){

                            event.setDamage(0D);
                            blue.setHealth(blue.getHealth() - 2);

                        }else if(damage > 3 && damage <= 6){

                            event.setDamage(0D);
                            blue.setHealth(blue.getHealth() - 4);

                        }else{

                            event.setDamage(0D);
                            blue.setHealth(blue.getHealth() - 6);

                        }

                    }

                    blue.setCustomName(setName(blue, ChatColor.BLUE));

                } else if (nmsDamaged instanceof RedMeleeZombie) {

                    RedMeleeZombie red = (RedMeleeZombie) nmsDamaged;

                    if(nmsDamager instanceof EntityPlayer){

                        event.setDamage(0D);
                        red.setHealth(red.getHealth() - 2);

                    }else if(damageEntity instanceof Arrow){

                        double damage = event.getDamage();

                        if(damage >= 0 && damage <= 3){

                            event.setDamage(0D);
                            red.setHealth(red.getHealth() - 2);

                        }else if(damage > 3 && damage <= 6){

                            event.setDamage(0D);
                            red.setHealth(red.getHealth() - 4);

                        }else{

                            event.setDamage(0D);
                            red.setHealth(red.getHealth() - 6);

                        }

                    }

                    red.setCustomName(setName(red, ChatColor.RED));


                } else {



                }

            } else {



            }
        }
    }

    private String setName(EntityZombie mz, ChatColor cc){

        String newName = cc + "";
        int health = (int) (mz.getHealth() / 2);
        int maxHealth = (int) (mz.getMaxHealth() / 2);

        for (int i = 0; i < health; i++) {

            newName += "█";

        }

        if (health < maxHealth) {

            for (int j = health; j < maxHealth; j++) {

                newName += ChatColor.DARK_GRAY + "█";

            }

        }

        return newName;

    }

    private void createBlood(Entity damaged) {
        Location loc = damaged.getLocation();
        for(double y = 0; y <= 1; y+=0.05) {
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.SLIME, true, (float) loc.getX(), (float) (loc.getY() + y), (float) loc.getZ(), 0, 0, 0, 0, 1);
            for(Player online : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer)online).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

}

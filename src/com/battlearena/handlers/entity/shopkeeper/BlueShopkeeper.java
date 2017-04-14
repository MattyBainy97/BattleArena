package com.battlearena.handlers.entity.shopkeeper;

import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_11_R1.World;
import org.bukkit.ChatColor;

public class BlueShopkeeper extends Shopkeeper {

    public BlueShopkeeper(World world){

        super(world);
        this.setCustomName(ChatColor.BLUE + "Rathnar the Terrible");

    }

}

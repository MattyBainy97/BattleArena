package com.battlearena.handlers.entity.shopkeeper;

import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_11_R1.World;
import org.bukkit.ChatColor;

public class RedShopkeeper extends Shopkeeper {

    public RedShopkeeper(World world){

        super(world);
        this.setCustomName(ChatColor.RED + "Valzothar the Brave");

    }

}

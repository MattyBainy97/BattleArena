package com.battlearena.handlers.entity.zombie;

import com.battlearena.handlers.entity.pathfindergoals.PathfinderGoalWalkToLoc;
import net.minecraft.server.v1_11_R1.*;
import net.minecraft.server.v1_11_R1.World;
import org.bukkit.*;

public class RedMeleeZombie extends MeleeZombie {

    public RedMeleeZombie(World world){

        super(world);

        this.goalSelector.a(1, new PathfinderGoalWalkToLoc(this, new Location(Bukkit.getWorld("BattleArena"), 649.5, 4.0, -28.5), 1D));
        this.setCustomName(ChatColor.RED + "██████");
        this.setHelmet(Color.RED);

    }

}

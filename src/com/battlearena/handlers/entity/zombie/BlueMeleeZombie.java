package com.battlearena.handlers.entity.zombie;

import com.battlearena.handlers.entity.pathfindergoals.PathfinderGoalWalkToLoc;
import net.minecraft.server.v1_11_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_11_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_11_R1.PathfinderGoalZombieAttack;
import net.minecraft.server.v1_11_R1.World;
import org.bukkit.*;

public class BlueMeleeZombie extends MeleeZombie {

    public BlueMeleeZombie(World world){

        super(world);

        this.goalSelector.a(0, new PathfinderGoalWalkToLoc(this, new Location(Bukkit.getWorld("BattleArena"), 649.5, 4.0, -4.5),0.1D));
        this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(this, RedMeleeZombie.class, true));
        this.setCustomName(ChatColor.BLUE + "██████");
        this.setHelmet(Color.BLUE);

    }

}

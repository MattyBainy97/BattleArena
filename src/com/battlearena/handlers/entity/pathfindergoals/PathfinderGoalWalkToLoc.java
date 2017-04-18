package com.battlearena.handlers.entity.pathfindergoals;

import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Location;

public class PathfinderGoalWalkToLoc extends PathfinderGoal{

    private double speed;
    private EntityInsentient entity;
    private Location loc;
    private BlockPosition blockPosition;

    public PathfinderGoalWalkToLoc(EntityInsentient entity, Location loc, double speed) {

        this.entity = entity;
        this.loc = loc;
        this.speed = speed;
        a(5);

    }

    public boolean a(){

        this.entity.onGround = true;

        this.blockPosition = new BlockPosition(loc.getX(), loc.getY(), loc.getZ());

        return true;

    }

    public void c() {

        this.entity.getNavigation().a(blockPosition.getX() + 0.5, blockPosition.getY(), blockPosition.getZ() + 0.5, speed);

    }

}

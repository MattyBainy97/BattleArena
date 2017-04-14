package com.battlearena.handlers.entity.pathfindergoals;

import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Location;

public class PathfinderGoalWalkToLoc extends PathfinderGoal{

    private double speed;
    private EntityInsentient entity;
    private Location loc;
    private NavigationAbstract navigation;

    public PathfinderGoalWalkToLoc(EntityInsentient entity, Location loc, double speed)
    {

        this.entity = entity;
        this.loc = loc;
        this.speed = speed;
        this.navigation = this.entity.getNavigation();

    }

    @Override
    public boolean a(){

        return true;

    }

    @Override
    public void c()
    {

        PathEntity pathEntity = this.navigation.a(loc.getX(), loc.getY(), loc.getZ());
        this.navigation.a(pathEntity, speed);

    }

    @Override
    public void e()
    {

        PathEntity pathEntity = this.navigation.a(loc.getX(), loc.getY(), loc.getZ());
        this.navigation.a(pathEntity, speed);

    }

}

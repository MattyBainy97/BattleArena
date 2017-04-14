package com.battlearena.handlers.entity.shopkeeper;

import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Color;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Set;

import static com.battlearena.utils.PluginUtilities.getPrivateField;

abstract class Shopkeeper extends EntityEvoker{

    public Shopkeeper(World world){

        super(world);

        Set goalB = (Set)getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
        Set goalC = (Set)getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        Set targetB = (Set)getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        Set targetC = (Set)getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();

        this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 3.0F, 1.0F));

        this.getAttributeInstance(GenericAttributes.c).setValue(1.0D);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(1000000.0D);
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(2.0D);

        this.setSilent(true);
        this.setCustomNameVisible(true);
        this.setInvulnerable(true);

    }

}

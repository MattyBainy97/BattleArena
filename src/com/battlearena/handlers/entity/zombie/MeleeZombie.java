package com.battlearena.handlers.entity.zombie;

import net.minecraft.server.v1_11_R1.*;
import org.bukkit.Color;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Set;

import static com.battlearena.utils.PluginUtilities.getPrivateField;

abstract class MeleeZombie extends EntityZombie{

    public MeleeZombie(World world){

        super(world);

        Set goalB = (Set)getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
        goalB.clear();
        Set goalC = (Set)getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
        goalC.clear();
        Set targetB = (Set)getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
        targetB.clear();
        Set targetC = (Set)getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
        targetC.clear();

        this.getAttributeInstance(GenericAttributes.c).setValue(1.0D);
        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(12.0D);
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(2.0D);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.1D);

        this.setHealth(12.0F);

        this.setSilent(true);
        this.setBaby(true);
        this.setCustomNameVisible(true);

    }

    protected void setHelmet(Color color) {

        org.bukkit.inventory.ItemStack helmet = new org.bukkit.inventory.ItemStack(org.bukkit.Material.LEATHER_HELMET);
        LeatherArmorMeta helmIM = (LeatherArmorMeta) helmet.getItemMeta();
        helmIM.setColor(color);
        helmet.setItemMeta(helmIM);
        LivingEntity en = (LivingEntity) this.getBukkitEntity();
        EntityEquipment ee = en.getEquipment();
        ee.setHelmetDropChance(0);
        ee.setHelmet(helmet);

    }

}

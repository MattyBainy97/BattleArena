package com.battlearena.handlers.entity;

import com.battlearena.handlers.entity.zombie.BlueMeleeZombie;
import com.battlearena.handlers.entity.shopkeeper.BlueShopkeeper;
import com.battlearena.handlers.entity.zombie.RedMeleeZombie;
import com.battlearena.handlers.entity.shopkeeper.RedShopkeeper;
import net.minecraft.server.v1_11_R1.*;
import org.bukkit.entity.EntityType;

public enum CustomEntityType {

    RED_MELEE_ZOMBIE("Red_Melee_Zombie", 54, EntityType.ZOMBIE, EntityZombie.class, RedMeleeZombie.class),
    BLUE_MELEE_ZOMBIE("Blue_Melee_Zombie", 54, EntityType.ZOMBIE, EntityZombie.class, BlueMeleeZombie.class),
    RED_SHOPKEEPER("Red_Shopkeeper", 34, EntityType.EVOKER, EntityEvoker.class, RedShopkeeper.class),
    BLUE_SHOPKEEPER("Blue_Shopkeeper", 34, EntityType.EVOKER, EntityEvoker.class, BlueShopkeeper.class);

    private String name;
    private int id;
    private EntityType entityType;
    private Class<?> nmsClass;
    private Class<?> customClass;
    private MinecraftKey key;
    private MinecraftKey oldKey;

    private CustomEntityType(String name, int id, EntityType entityType, Class<?> nmsClass, Class<?> customClass){
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
        this.key = new MinecraftKey(name);
        this.oldKey = EntityTypes.b.b((Class<? extends Entity>) nmsClass);
    }

    public String getName(){
        return this.name;
    }

    public int getID(){
        return this.id;
    }

    public EntityType getEntityType(){
        return this.entityType;
    }

    public Class<?> getNMSClass(){
        return this.nmsClass;
    }

    public Class<?> getCustomClass(){
        return this.customClass;
    }

    public static void registerEntities(){
        for (CustomEntityType cet : values()){

            cet.register();

        }
    }

    public static void unregisterEntities(){
        for (CustomEntityType cet : values()){

            cet.unregister();

        }
    }

    @SuppressWarnings("unchecked")
    private void register() {
        EntityTypes.d.add(key);
        EntityTypes.b.a(id, key, (Class<? extends Entity>) customClass);
    }

    @SuppressWarnings("unchecked")
    private void unregister() {
        EntityTypes.d.remove(key);
        EntityTypes.b.a(id, oldKey, (Class<? extends Entity>) nmsClass);
    }

}

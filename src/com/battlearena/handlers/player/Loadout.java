package com.battlearena.handlers.player;

public enum Loadout {

    ARCHER("Archer", 100, 10, 10),
    WARRIOR("Warrior", 200, 20, 20),
    MAGE("Mage", 50, 10, 10),
    SOMETHING("Something", 100, 10, 10);

    String name;
    int health;
    int armor;
    int mr;

    Loadout(String name, int health, int armor, int mr){

        this.name = name;
        this.health = health;
        this.armor = armor;
        this.health = health;

    }

    public String getName(){

        return name;

    }

    public int getHealth(){

        return health;

    }

    public int getArmor(){

        return armor;

    }

    public int getMr() {

        return mr;

    }

}

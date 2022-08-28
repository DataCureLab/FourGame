package com.datacure.fourGame.utils;

public class PlayerImp implements Player {
    private String name;
    private Disk disk;

    public String getName() {
        return name;
    }

    public Disk getDisk() {
        return disk;
    }

    public PlayerImp(String name, Disk disk) {
        this.name = name;
        this.disk = disk;
    }
}

package com.datacure.fourGame.utils;

public class PlayerImpl implements Player {
    private String name;
    private Disk disk;

    public String getName() {
        return name;
    }

    public Disk getDisk() {
        return disk;
    }

    public PlayerImpl(String name, Disk disk) {
        this.name = name;
        this.disk = disk;
    }
}

package com.datacure.utils;

import com.datacure.Disk;
import com.datacure.utils.Player;

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

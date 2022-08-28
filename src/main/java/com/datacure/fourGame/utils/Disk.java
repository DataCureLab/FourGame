package com.datacure.fourGame.utils;

public enum Disk {
    RED, GREEN;

    @Override
    public String toString() {
        return this.equals(RED) ? "R" : "G";
    }
}

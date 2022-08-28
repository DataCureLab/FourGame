package com.datacure.fourGame.gameplay;

import com.datacure.fourGame.utils.Disk;

import java.util.Arrays;

public interface GamePlay {
    void clearPlaying();
    boolean isFull();
    boolean putDisk(Disk disk, int col) throws GameIncorrectIntroduce;
}

package com.datacure;

public class Game {
    public static void main(String[] args) throws Exception {
        System.out.println("Loading...");
        GamePlay gamePlay = new GamePlay();
        for (int i = 0; i < 5; i++) {
            gamePlay.putDisk(Disk.RED, 4);
            System.out.println(gamePlay.getPlayingFieldString());
        }
    }
}

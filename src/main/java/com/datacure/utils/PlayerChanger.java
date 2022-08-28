package com.datacure.utils;

import com.datacure.utils.Player;

public class PlayerChanger {
    private Player players[];
    private int currentPlayer = 0;

    public PlayerChanger(Player[] players) {
        this.players = players;
    }

    public Player change() {
        Player res = players[currentPlayer++];

        if (currentPlayer >= players.length) {
            currentPlayer = 0;
        }
        return res;
    }

    public void reset () {
        currentPlayer = 0;
    }
}

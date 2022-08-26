package com.datacure;

public class GamePlay {
    private final static int HEIGHT = 7;
    private final static int WIDTH = 6;

    private Disk[][] playingField = new Disk[WIDTH][HEIGHT];

    public void putDisk(Disk disk, int row) throws Exception {
        if ((row < 0)||(row > HEIGHT)) {
            throw new Exception("Incorrect row number : " + row + "[0," + HEIGHT + "]");
        }
        for (int i = 0; i < WIDTH; i++) {
            if (playingField[i][row] == null) {
                playingField[i][row] = disk;
                return;
            }
        }
        throw new Exception("This row is full");
    }

    public String getPlayingFieldString() {
        StringBuilder res = new StringBuilder();
        for (int i = WIDTH - 1; i >= 0; i--) {
            res.append(getPlayingLine(i));
            res.append("\r\n");
        }
        return res.toString();
    }

    private StringBuilder getPlayingLine(int number) {
        StringBuilder res = new StringBuilder("|");
        for (Disk disk : playingField[number]) {
            res.append(disk == null ? " " : disk.toString());
            res.append("|");
        }
        return res;
    }

}

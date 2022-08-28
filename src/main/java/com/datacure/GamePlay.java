package com.datacure;

import java.util.Arrays;

public class GamePlay {
    private final int height;
    private final int width;
    private final int sizeWin;

    private Disk[][] playingField;

    public GamePlay(int width, int height, int sizeWin) {
        this.width = width;
        this.height = height;
        this.sizeWin = sizeWin;
        playingField = new Disk[width][height];
    }

    public void clearPlaying() {
        Arrays.stream(playingField).forEach(f -> Arrays.fill(f, null));
    }

    public boolean isFill () {
        for (Disk disk : playingField[width -1]) {
            if (disk == null) return false;
        }
        return true;
    }

    public boolean putDisk(Disk disk, int col) throws GameIncorrectIntroduce {
        if ((col < 0)||(col > height)) {
            throw new GameIncorrectIntroduce("Incorrect row number : " + col + "[0," + height + "]");
        }
        int row;
        for (row = 0; row < width; row++) {
            if (playingField[row][col] == null) {
                playingField[row][col] = disk;
                return (getCountHorizontal(row, col) >= sizeWin) || (getCountDiagonalR(row, col) >= sizeWin) ||
                        (getCountDiagonalL(row, col) >= sizeWin) || (row >= sizeWin - 1 && getCountDown(row, col) >= sizeWin);
            }
        }
        throw new GameIncorrectIntroduce("This row is full");
    }

    private int getCountDown(int row, int col) {
        int count = 1;
        Disk disk = playingField[row][col];
        for (int i = row - 1; i >= 0; i--) {
            if (disk.equals(playingField[i][col])) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int getCountHorizontal(int row, int col) {
        int count = 1;
        Disk disk = playingField[row][col];
        for (int i = col - 1; i >= 0; i--) {
            if (disk.equals(playingField[row][i])) {
                count++;
            } else {
                break;
            }
        }
        for (int i = col + 1; i < height; i++) {
            if (disk.equals(playingField[row][i])) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private int getCountDiagonalR(int row, int col) {
        int count = 1;
        Disk disk = playingField[row][col];

        for (int i = 1; (row + i < width) && (col + i < height); i++) {
            if (disk.equals(playingField[row+i][col+i])) {
                count++;
            } else {
                break;
            }
        }
        for (int i = 1; (row - i >= 0) && (col - i >= 0); i++) {
            if (disk.equals(playingField[row-i][col-i])) {
                count++;
            } else {
                break;
            }
        }
        
        return count;
    }

    private int getCountDiagonalL(int row, int col) {
        int count = 1;
        Disk disk = playingField[row][col];

        for (int i = 1; (row + i < width) && (col - i >= 0); i++) {
            if (disk.equals(playingField[row+i][col-i])) {
                count++;
            } else {
                break;
            }
        }
        for (int i = 1; (row - i >= 0) && (col + i < width); i++) {
            if (disk.equals(playingField[row-i][col+i])) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = width - 1; i >= 0; i--) {
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

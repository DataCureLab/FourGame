package com.datacure;

import java.util.Arrays;

public class GamePlay {
    private static final int HEIGHT = 7;
    private static final int WIDTH = 6;
    private static final int SIZE_WIN = 4;

    private Disk[][] playingField = new Disk[WIDTH][HEIGHT];

    public void clearPlaying() {
        Arrays.stream(playingField).forEach(f -> Arrays.fill(f, null));
    }

    public boolean isFill () {
        for (Disk disk : playingField[WIDTH-1]) {
            if (disk == null) return false;
        }
        return true;
    }

    public boolean putDisk(Disk disk, int col) throws GameIncorrectIntroduce {
        if ((col < 0)||(col > HEIGHT)) {
            throw new GameIncorrectIntroduce("Incorrect row number : " + col + "[0," + HEIGHT + "]");
        }
        int row;
        for (row = 0; row < WIDTH; row++) {
            if (playingField[row][col] == null) {
                playingField[row][col] = disk;
                return (getCountHorizontal(row, col) >= SIZE_WIN) || (getCountDiagonalR(row, col) >= SIZE_WIN) ||
                        (getCountDiagonalL(row, col) >= SIZE_WIN) || (row >= SIZE_WIN - 1 && getCountDown(row, col) >= SIZE_WIN);
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
        for (int i = col + 1; i < HEIGHT; i++) {
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

        for (int i = 1; (row + i < WIDTH) && (col + i < HEIGHT); i++) {
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

        for (int i = 1; (row + i < WIDTH) && (col - i >= 0); i++) {
            if (disk.equals(playingField[row+i][col-i])) {
                count++;
            } else {
                break;
            }
        }
        for (int i = 1; (row - i >= 0) && (col + i < WIDTH); i++) {
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

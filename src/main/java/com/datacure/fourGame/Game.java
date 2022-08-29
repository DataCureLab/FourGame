package com.datacure.fourGame;

import com.datacure.fourGame.gameplay.GamePlay;
import com.datacure.fourGame.gameplay.RowIsFull;
import com.datacure.fourGame.utils.Disk;
import com.datacure.fourGame.gameplay.GameIncorrectIntroduce;
import com.datacure.fourGame.gameplay.GamePlayImpl;
import com.datacure.fourGame.utils.PlayerChanger;
import com.datacure.fourGame.utils.Player;
import com.datacure.fourGame.utils.PlayerImpl;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.jline.utils.NonBlockingReader;
import java.io.IOException;

public class Game {
    private static final int ASCII_1 = 49;
    private static final int ASCII_ESC = 27;
    private static final int ASCII_LF = 10;
    private static final int ASCII_CR = 13;

    private static final int PLAY_HEIGHT = 7;
    private static final int PLAY_WIDTH = 6;
    private static final int PLAY_SIZE_WIN = 4;

    private PlayerChanger playerChanger;
    private GamePlay gamePlay;

    public Game(PlayerChanger playerChanger, GamePlay gamePlay) {
        this.playerChanger = playerChanger;
        this.gamePlay = gamePlay;
    }

    private void init() {
        playerChanger.reset();
        gamePlay.clearPlaying();
        System.out.println(gamePlay);
    }

    public void run(Terminal terminal) throws IOException, GameIncorrectIntroduce {
        int key = 0;
        NonBlockingReader reader = terminal.reader();
        init();
        Player player = playerChanger.change();

        while (key != ASCII_ESC) {
            // TODO it is temporary for DUMB terminal, need remove
            if (key != ASCII_LF && key != ASCII_CR) {
                System.out.println(player.getName() + " press 1-7 for put disk or ESC to exit");
                System.out.println();
            }
            key = reader.read();

            if (key >= ASCII_1 && key <= ASCII_1+PLAY_HEIGHT) {
                boolean win = false;
                try {
                    win = gamePlay.putDisk(player.getDisk(), key - ASCII_1);
                } catch (RowIsFull ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }
                terminal.puts(InfoCmp.Capability.clear_screen);
                System.out.println();
                System.out.println(gamePlay);
                if (win) {
                    System.out.println("!!! " + player.getName() + " IS WINNER!!!");
                    key = reload(terminal);
                } else if (gamePlay.isFull()) {
                    System.out.println("!!!DRAW!!!");
                    key = reload(terminal);
                }
                player = playerChanger.change();
            }
        }
    }

    private int reload(Terminal terminal) throws IOException {
        int key;
        System.out.println("Press any key for new game or ESC for exit");
        key = terminal.reader().read();
        if (key != ASCII_ESC) {
            terminal.puts(InfoCmp.Capability.clear_screen);
            System.out.println();
            init();
        }
        return key;
    }

    public static void main(String[] args) throws Exception {
        Terminal terminal = TerminalBuilder.terminal();
        terminal.enterRawMode();
        terminal.puts(InfoCmp.Capability.clear_screen);
        System.out.println("Loading...");
        Player[] players = {new PlayerImpl("Player1", Disk.RED), new PlayerImpl("Player2", Disk.GREEN)};
        Game game = new Game(new PlayerChanger(players), new GamePlayImpl(PLAY_WIDTH, PLAY_HEIGHT, PLAY_SIZE_WIN));
        game.run(terminal);
    }
}

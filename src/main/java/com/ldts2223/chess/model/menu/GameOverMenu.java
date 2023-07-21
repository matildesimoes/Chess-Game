package com.ldts2223.chess.model.menu;

import java.util.Arrays;

public class GameOverMenu extends Menu {

    public GameOverMenu(String message) {
        this.setTitle(message);
        this.setEntries(Arrays.asList("Play Again","Back To Menu"));
    }
}

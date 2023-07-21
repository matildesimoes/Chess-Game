package com.ldts2223.chess.model.menu;

import java.util.Arrays;

public class MainMenu extends Menu {

    public MainMenu() {
        this.setTitle("Menu");
        this.setEntries(Arrays.asList("Start", "Settings", "Quit"));
    }
}

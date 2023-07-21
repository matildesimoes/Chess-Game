package com.ldts2223.chess.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class MainMenuTest {

    private MainMenu mainMenu = new MainMenu();

    @Test
    public void setEntries() {
        mainMenu.setEntries(Arrays.asList("Start", "Change Game Settings", "Exit"));
        Assertions.assertEquals("Change Game Settings",mainMenu.getEntry(1));
    }
    @Test
    public void checkNumberOfEntries() {
        int entries = mainMenu.getNumberEntries();
        Assertions.assertEquals(3, entries);
    }
    @Test
    public void checkEntries() {
        Assertions.assertEquals("Start", mainMenu.getEntry(0));
    }
}

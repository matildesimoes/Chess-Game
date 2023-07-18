package com.ldts2223.chess.model.menu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainMenuTest {
    private MainMenu mainMenu = new MainMenu();

    @Test
    public void setEntries() {
        mainMenu.setEntries(Arrays.asList("Start", "Change Game Settings", "Exit"));
        assertEquals("Change Game Settings",mainMenu.getEntry(1));
    }
    @Test
    public void checkNumberOfEntries() {
        int entries = mainMenu.getNumberEntries();
        assertEquals(3, entries);
    }
    @Test
    public void checkEntries() {
        assertEquals("Start", mainMenu.getEntry(0));
    }
    @Test
    public void isSelected() {
        assertEquals(true,mainMenu.isSelected(0));
    }
}

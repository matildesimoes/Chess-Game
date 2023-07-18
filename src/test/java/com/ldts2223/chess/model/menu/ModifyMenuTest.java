package com.ldts2223.chess.model.menu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifyMenuTest {

    private ModifyMenu modifyMenu = new ModifyMenu();

    @Test
    public void setEntries() {
        modifyMenu.setEntries(Arrays.asList("Change Game Rules", "Change Pieces", "Go Back"));
        assertEquals("Go Back",modifyMenu.getEntry(2));
    }
    @Test
    public void checkNumberOfEntries() {
        int entries = modifyMenu.getNumberEntries();
        assertEquals(3, entries);
    }
    @Test
    public void checkEntries() {
        assertEquals("Change Game Rules", modifyMenu.getEntry(0));
    }
    @Test
    public void isSelected() {
        assertEquals(true,modifyMenu.isSelected(0));
    }
}
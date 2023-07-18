package com.ldts2223.chess.model.menu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRuleMenuTest {
    private GameRulesMenu gameRulesMenu = new GameRulesMenu();

    @Test
    public void setEntries() {
        gameRulesMenu.setEntries(Arrays.asList("Explosive Pieces", "Chess Check", "Playable Captured Pieces"));
        assertEquals("Chess Check",gameRulesMenu.getEntry(1));
    }
    @Test
    public void checkNumberOfEntries() {
        int entries = gameRulesMenu.getNumberEntries();
        assertEquals(3, entries);
    }
    @Test
    public void checkEntries() {
        assertEquals("Explosive Pieces", gameRulesMenu.getEntry(0));
    }
    @Test
    public void isSelected() {
        assertEquals(true,gameRulesMenu.isSelected(0));
    }
}
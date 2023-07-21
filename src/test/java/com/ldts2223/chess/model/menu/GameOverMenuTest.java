package com.ldts2223.chess.model.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class GameOverMenuTest {

    private GameOverMenu gameOverMenu = new GameOverMenu("Checkmate! Black Won!");

    @Test
    public void setTitle(){
        gameOverMenu.setTitle("Checkmate! White Won!");
        Assertions.assertEquals("Checkmate! White Won!",gameOverMenu.getTitle());
    }
    @Test
    public void setEntries() {
        gameOverMenu.setEntries(Arrays.asList("Play Again", "Exit"));
        Assertions.assertEquals("Play Again", gameOverMenu.getEntry(0));
    }
    @Test
    public void checkNumberOfEntries() {
        int entries = gameOverMenu.getNumberEntries();
        Assertions.assertEquals(2, entries);
    }

    @Test
    public void checkEntries() {
        Assertions.assertEquals("Play Again", gameOverMenu.getEntry(0));
    }

    @Test
    public void setEntryX(){
        gameOverMenu.setEntryX(7);
        Assertions.assertEquals(7,gameOverMenu.getEntryX());
    }

    @Test
    public void getEntryY(){
        Assertions.assertEquals(6,gameOverMenu.getEntryY());
    }

    @Test
    public void setSelectedEntry(){
        gameOverMenu.setSelectedEntry(0);
        Assertions.assertEquals("Play Again",gameOverMenu.getEntry(0));
    }

    @Test
    public void isSelected(){
        gameOverMenu.setSelectedEntry(0);
        Assertions.assertTrue(gameOverMenu.isSelected(0));
    }
}

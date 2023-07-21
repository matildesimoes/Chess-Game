package com.ldts2223.chess.model.game.match;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class SettingsTest {

    private Settings settings = Settings.getInstance();

    @BeforeEach
    public void setUp(){
        settings = Settings.getInstance();
    }

    @AfterEach
    public void resetSettings(){
        Settings.getInstance().reset();
    }

    @Test
    public void getInstance() {
        Assertions.assertSame(Settings.getInstance(), Settings.getInstance());
        Assertions.assertNotNull(Settings.getInstance());
    }

    @Test
    public void incrementWhitePlayer() {
        Assertions.assertEquals("User", settings.getWhitePlayer());
        settings.incrementWhitePlayer();
        Assertions.assertEquals("Easy Bot", settings.getWhitePlayer());
        settings.incrementWhitePlayer();
        Assertions.assertEquals("Medium Bot", settings.getWhitePlayer());
        settings.incrementWhitePlayer();
        Assertions.assertEquals("User", settings.getWhitePlayer());
    }

    @Test
    public void decrementWhitePlayer() {
        Assertions.assertEquals("User", settings.getWhitePlayer());
        settings.decrementWhitePlayer();
        Assertions.assertEquals("Medium Bot", settings.getWhitePlayer());
        settings.decrementWhitePlayer();
        Assertions.assertEquals("Easy Bot", settings.getWhitePlayer());
        settings.decrementWhitePlayer();
        Assertions.assertEquals("User", settings.getWhitePlayer());
    }

    @Test
    public void incrementBlackPlayer() {
        Assertions.assertEquals("Medium Bot", settings.getBlackPlayer());
        settings.incrementBlackPlayer();
        Assertions.assertEquals("User", settings.getBlackPlayer());
        settings.incrementBlackPlayer();
        Assertions.assertEquals("Easy Bot", settings.getBlackPlayer());
        settings.incrementBlackPlayer();
        Assertions.assertEquals("Medium Bot", settings.getBlackPlayer());
    }

    @Test
    public void decrementBlackPlayer() {
        Assertions.assertEquals("Medium Bot", settings.getBlackPlayer());
        settings.decrementBlackPlayer();
        Assertions.assertEquals("Easy Bot", settings.getBlackPlayer());
        settings.decrementBlackPlayer();
        Assertions.assertEquals("User", settings.getBlackPlayer());
        settings.decrementBlackPlayer();
        Assertions.assertEquals("Medium Bot", settings.getBlackPlayer());
    }

    @Test
    public void gameMode(){
        Assertions.assertEquals("Traditional", settings.getGamemode().getTitle());
        settings.incrementGamemode();
        Assertions.assertEquals("Explosive", settings.getGamemode().getTitle());
        settings.incrementGamemode();
        Assertions.assertEquals("Traditional", settings.getGamemode().getTitle());
        settings.decrementGamemode();
        Assertions.assertEquals("Explosive", settings.getGamemode().getTitle());
        settings.decrementGamemode();
        Assertions.assertEquals("Traditional", settings.getGamemode().getTitle());
    }

    @Test
    public void time(){
        Assertions.assertEquals(600, settings.getTime());
        settings.increaseTime();
        Assertions.assertEquals(630, settings.getTime());
        settings.decreaseTime();
        Assertions.assertEquals(600, settings.getTime());
    }
}
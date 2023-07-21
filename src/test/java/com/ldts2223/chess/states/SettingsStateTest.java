package com.ldts2223.chess.states;

import com.ldts2223.chess.model.game.match.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SettingsStateTest {

    private SettingsState settingsState;
    private Settings settings;

    @BeforeEach
    public void setUp(){
        settingsState = new SettingsState(settings);
    }
    @Test
    public void testGetModel(){
        Assertions.assertEquals(settings,settingsState.getModel());
    }
}

package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import com.ldts2223.chess.model.game.match.player.bot.CaptureBot;
import com.ldts2223.chess.viewer.match.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerViewerTest {

    private GUI gui;
    private Player playerUser;
    private Player playerBot;
    private PlayerViewer playerViewer;

    @BeforeEach
    void setUp() {
        playerUser = new User(20,true);
        playerBot= new CaptureBot(20,false);
        gui = Mockito.mock(GUI.class);
        playerViewer = new PlayerViewer();
    }

    @Test
    void drawElementsUser() {
        playerViewer.draw(playerUser, gui);

        Mockito.verify(gui, Mockito.times(1)).drawText(
                Mockito.any(Position.class), Mockito.any(), Mockito.eq("#FFFFFF"), Mockito.eq("#363636"));
    }

    @Test
    void drawElementsBot() {
        playerViewer.draw(playerBot, gui);

        Mockito.verify(gui, Mockito.times(1)).drawText(
                Mockito.any(Position.class), Mockito.any(), Mockito.eq("#FFFFFF"), Mockito.eq("#363636"));
    }
}

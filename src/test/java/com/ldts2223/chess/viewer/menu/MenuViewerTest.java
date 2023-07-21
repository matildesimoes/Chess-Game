package com.ldts2223.chess.viewer.menu;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.menu.GameOverMenu;
import com.ldts2223.chess.model.menu.MainMenu;
import com.ldts2223.chess.model.menu.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuViewerTest {

    private Menu mainMenu;
    private Menu gameOverMenu;
    private GUI gui;
    private MenuViewer mainMenuViewer;
    private MenuViewer gameOverMenuViewer;

    @BeforeEach
    void setUp() {
        mainMenu = new MainMenu();
        gameOverMenu = new GameOverMenu("Failed");
        gui = Mockito.mock(GUI.class);
        mainMenuViewer = new MenuViewer(mainMenu);
        gameOverMenuViewer = new MenuViewer(gameOverMenu);
    }

    @Test
    void drawMainMenu() {
        mainMenuViewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawTextCentered(
                Mockito.anyInt(), Mockito.anyString(), Mockito.eq("#FFFFFF"), Mockito.eq("#000000"));
        Mockito.verify(gui, Mockito.times(3)).drawText(
                Mockito.any(Position.class), Mockito.anyString(), Mockito.any(), Mockito.eq("#000000"));
    }

    @Test
    void drawGameOverMenu() {
        gameOverMenuViewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawTextCentered(
                Mockito.anyInt(), Mockito.eq("Failed"), Mockito.eq("#FFFFFF"), Mockito.eq("#000000"));
        Mockito.verify(gui, Mockito.times(2)).drawText(
                Mockito.any(Position.class), Mockito.anyString(), Mockito.any(), Mockito.eq("#000000"));
    }
}

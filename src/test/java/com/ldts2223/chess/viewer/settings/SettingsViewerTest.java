package com.ldts2223.chess.viewer.settings;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Settings;
import com.ldts2223.chess.viewer.Settings.SettingsViewer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SettingsViewerTest {

    @Test
    public void drawElements(){
        GUI gui = Mockito.mock(GUI.class);
        SettingsViewer viewer = new SettingsViewer(Settings.getInstance());
        viewer.drawElements(gui);

        Mockito.verify(gui).drawTextCentered(3, "Settings", "#FFFFFF", "#000000");
        Mockito.verify(gui).drawText(new Position(4, 13), "Back to Menu", "#FFFFFF", "#000000");

        Mockito.verify(gui).drawText(new Position(2, 6), "Mode: ", "#FFFFFF", "#000000");
        Mockito.verify(gui).drawText(new Position(9, 6), "- ", "#FFFFFF", "#000000");
        Mockito.verify(gui).drawText(new Position(11, 6), "Traditional", "#FFFFFF", "#000000");
        Mockito.verify(gui).drawText(new Position(22, 6), "+", "#FFFFFF", "#000000");
    }
}
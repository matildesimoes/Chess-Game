package com.ldts2223.chess.viewer.menu;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.menu.Menu;
import com.ldts2223.chess.viewer.Viewer;

public class MenuViewer extends Viewer<Menu>{

    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawTextCentered(3, getModel().getTitle(), "#FFFFFF", "#000000");

        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            Position position = new Position(getModel().getEntryX(), getModel().getEntryY() + i);
            gui.drawText(
                    new Position(getModel().getEntryX(), getModel().getEntryY() + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF",
                    "#000000");
        }
    }
}

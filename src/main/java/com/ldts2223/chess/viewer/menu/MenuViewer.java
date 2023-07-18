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
        gui.drawText(new Position(5, 5), "Menu", "#FFFFFF");

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(5, 7 + i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }

}

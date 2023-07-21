package com.ldts2223.chess.viewer.Settings;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Settings;
import com.ldts2223.chess.viewer.Viewer;

public class SettingsViewer extends Viewer<Settings> {

    public SettingsViewer(Settings settings) {
        super(settings);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawTextCentered(3, "Settings", "#FFFFFF", "#000000");
        Position backPos = new Position(getModel().getBackX(), getModel().getBackY());
        gui.drawText(backPos, getModel().getBackEntry(), "#FFFFFF", "#000000");

        int leftX = getModel().getDecreaseX();
        int rightX = getModel().getIncreaseX();
        int y = getModel().getEntryY();

        drawGameMode(gui, y, leftX, rightX);
        drawWhitePLayer(gui, y+1, leftX, rightX);
        drawBlackPLayer(gui, y+2, leftX, rightX);
        drawTime(gui, y+3, leftX, rightX);
    }

    private void drawWhitePLayer(GUI gui, int y, int leftX, int rightX) {
        gui.drawText(new Position(2, y), "White: ","#FFFFFF", "#000000");
        gui.drawText(new Position(leftX, y), "- ","#FFFFFF", "#000000");
        gui.drawText(new Position(leftX + 2, y), getModel().getWhitePlayer(), "#FFFFFF", "#000000");
        gui.drawText(new Position(rightX, y),"+", "#FFFFFF", "#000000");
    }

    private void drawBlackPLayer(GUI gui, int y, int leftX, int rightX) {
        gui.drawText(new Position(2, y), "Black: ","#FFFFFF", "#000000");
        gui.drawText(new Position(leftX, y), "- ","#FFFFFF", "#000000");
        gui.drawText(new Position(leftX + 2, y), getModel().getBlackPlayer(), "#FFFFFF", "#000000");
        gui.drawText(new Position(rightX, y), "+", "#FFFFFF", "#000000");
    }

    private void drawTime(GUI gui, int y, int leftX, int rightX){
        long time = getModel().getTime();
        String timeString = String.format("%02d", time / 60) + ":" + String.format("%02d", time % 60);
        gui.drawText(new Position(2, y), "Time: ", "#FFFFFF", "#000000");
        gui.drawText(new Position(leftX, y), "- ","#FFFFFF", "#000000");
        gui.drawText(new Position(leftX + 2, y), timeString , "#FFFFFF", "#000000");
        gui.drawText(new Position(rightX, y), "+", "#FFFFFF", "#000000");
    }

    private void drawGameMode(GUI gui, int y, int leftX, int rightX){
        gui.drawText(new Position(2, y), "Mode: ", "#FFFFFF", "#000000");
        gui.drawText(new Position(leftX, y), "- ","#FFFFFF", "#000000");
        gui.drawText(new Position(leftX + 2, y), getModel().getGamemode().getTitle(), "#FFFFFF", "#000000");
        gui.drawText(new Position(rightX, y), "+", "#FFFFFF", "#000000");
    }
}

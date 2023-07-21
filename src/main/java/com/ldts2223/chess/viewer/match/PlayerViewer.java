package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.player.Player;


public class PlayerViewer {

    protected void draw(Player player, GUI gui) {
        String minutes = String.format("%02d", player.getTime() / 60);
        String seconds = String.format("%02d", player.getTime() % 60);

        int y = player.isWhite() ? 9 : 2;
        gui.drawText(new Position(18,y),minutes + ":" + seconds,"#FFFFFF", "#363636");
    }
}

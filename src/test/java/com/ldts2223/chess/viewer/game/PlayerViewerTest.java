package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.player.Bot;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import com.ldts2223.chess.model.game.match.player.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class PlayerViewerTest {

    private Player user;
    private Player bot;
    
    private GUI gui;

    private PlayerViewer userViewer;
    private PlayerViewer botViewer;

    @BeforeEach
    void helper() {

        user = new User();
        user.addPiece(new Piece(new Position(2,3), 'P'));
        user.addPiece(new Piece(new Position(5,2), 'T'));
        userViewer = new PlayerViewer(user);

        bot = new Bot();
        bot.addPiece(new Piece(new Position(10,14), 'M'));
        bot.addPiece(new Piece(new Position(2,4), 'N'));
        botViewer = new PlayerViewer(bot);

        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawUserPieces() throws IOException {
        userViewer.draw(gui);

        Mockito.verify(
                gui,
                Mockito.times(1)).drawPiece(new Position(2,3), 'P');

        Mockito.verify(
                gui,
                Mockito.times(1)).drawPiece(new Position(5,2), 'T');

        Mockito.verify(
                gui,
                Mockito.times(2)).drawPiece(Mockito.any(Position.class), Mockito.anyChar());
    }

    @Test
    void drawBotPieces() throws IOException {
        botViewer.draw(gui);

        Mockito.verify(
                gui,
                Mockito.times(1)).drawPiece(new Position(10,14), 'M');

        Mockito.verify(
                gui,
                Mockito.times(1)).drawPiece(new Position(2,4), 'N');

        Mockito.verify(
                gui,
                Mockito.times(2)).drawPiece(Mockito.any(Position.class), Mockito.anyChar());
    }
}

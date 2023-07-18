package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Board;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.player.Bot;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import com.ldts2223.chess.model.game.match.player.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MatchViewerTest {

    private GUI gui;
    private Match match;

    private MatchViewer matchViewer;

    @BeforeEach
    void helper(){
        gui = Mockito.mock(GUI.class);
        match = new Match();
        matchViewer = new MatchViewer(match);

        Player p1 = new Bot();
        p1.addPiece(new Piece(new Position(2,3), 'P'));
        p1.addPiece(new Piece(new Position(5,6), 'P'));
        p1.addPiece(new Piece(new Position(4,3), 'T'));
        Player p2 = new User();
        p2.addPiece(new Piece(new Position(4,3), 'B'));
        p2.addPiece(new Piece(new Position(1,2), 'C'));

        Player p3 = new Bot();
        p3.addPiece(new Piece(new Position(3,5), 'P'));
        Player p4 = new Bot();
        p4.addPiece(new Piece(new Position(1,1), 'P'));
        p4.addPiece(new Piece(new Position(8,5), 'R'));

        match.addBoard(
                new Board(8, 8),
                new ArrayList<Player>(Arrays.asList(p1, p2)));

        match.addBoard(
                new Board(10, 10),
                new ArrayList<Player>(Arrays.asList(p3, p4)));

    }

    @Test
    void drawBoards() throws IOException {
        matchViewer.draw(gui);

        Mockito.verify(gui, Mockito.times(1)).drawBoard(8,8);
        Mockito.verify(gui, Mockito.times(1)).drawBoard(10,10);
        Mockito.verify(gui, Mockito.times(2)).drawBoard(Mockito.anyInt(),Mockito.anyInt());
    }

    @Test
    void drawPieces() throws IOException {
        matchViewer.draw(gui);

        Mockito.verify(
                gui,
                Mockito.times(8)).drawPiece(Mockito.any(Position.class),Mockito.anyChar());
    }




}

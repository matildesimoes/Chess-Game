package com.ldts2223.chess.model;

import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import com.ldts2223.chess.model.game.match.player.pieces.Piece;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTest {

    private Player player;
    private List<Piece> pieces;
    private List<Piece> expected;

    @BeforeEach
    void helper(){

        pieces = new ArrayList<Piece>(Arrays.asList(
                new Piece(new Position(2,1), 'P'),
                new Piece(new Position(0,5), 'M')));

        player = new User();

        player.setPieces(pieces);

        expected = new ArrayList<Piece>(Arrays.asList(
                new Piece(new Position(2,1), 'P'),
                new Piece(new Position(0,5), 'M'),
                new Piece(new Position(3,7), 'E')));
    }

    @Test
    void addPieces() throws IOException {
        player.addPiece(new Piece(new Position(3,7), 'E'));
        Assertions.assertEquals(expected, player.getPieces());
    }

}

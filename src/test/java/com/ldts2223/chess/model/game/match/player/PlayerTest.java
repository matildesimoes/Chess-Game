package com.ldts2223.chess.model.game.match.player;

import java.util.Arrays;
import java.util.HashSet;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerTest {

    private Player player;
    private HashSet<Piece> pieces;
    private boolean white = true;

    @BeforeEach
    void helper(){
        pieces = new HashSet<>(Arrays.asList(
                Mockito.spy(new Pawn(new Position(2,1), white)),
                Mockito.spy(new King(new Position(0,5), white))));

        player = new User(10,white);

        player.setPieces(pieces);
    }

    @Test
    public void testSetTime() {
        long newTime = 700;
        player.setTime(newTime);
        Assertions.assertEquals(newTime, player.getTime());
    }

    @Test
    public void testIsWhite() {
        Player whitePlayer = new User(600, true);
        Player blackPlayer = new User(600, false);
        Assertions.assertTrue(whitePlayer.isWhite());
        Assertions.assertFalse(blackPlayer.isWhite());
    }

    @Test
    public void testSetKing() {
        King king = new King(new Position(0, 0), true);
        player.setKing(king);
        Assertions.assertEquals(king, player.getKing());
    }

    @Test
    public void testGetPieces() {
        HashSet<Piece> pieces = new HashSet<>();
        player.setPieces(pieces);
        Assertions.assertEquals(pieces, player.getPieces());
    }

    @Test
    public void testSetPieces() {
        HashSet<Piece> pieces = new HashSet<>();
        player.setPieces(pieces);
        Assertions.assertEquals(pieces, player.getPieces());
    }

    @Test
    public void testAddPiece() {
        Pawn piece = new Pawn(new Position(0, 0), true);
        player.addPiece(piece);
        Assertions.assertTrue(player.getPieces().contains(piece));
    }

    @Test
    public void testLosePiece() {
        Pawn piece1 = new Pawn(new Position(0, 0), true);
        Pawn piece2 = new Pawn(new Position(1, 1), true);
        player.addPiece(piece1);
        player.addPiece(piece2);
        player.losePiece(new Position(0, 0));
        Assertions.assertFalse(player.getPieces().contains(piece1));
        Assertions.assertTrue(player.getPieces().contains(piece2));
    }

    @Test
    public void testGetLastMove() {
        long lastMove = 1000;
        player.setLastMove(lastMove);
        Assertions.assertEquals(lastMove, player.getLastMove());
    }

    @Test
    public void testUpdateTime() {
        long currentTime = 1000;
        player.setLastMove(0);
        player.updateTime(currentTime);
        Assertions.assertEquals(599, player.getTime());
        player.updateTime(currentTime + 500);
        Assertions.assertEquals(599, player.getTime());
    }

    @Test
    public void testSetLastMove() {
        long lastMove = 1000;
        player.setLastMove(lastMove);
        Assertions.assertEquals(lastMove, player.getLastMove());
    }

    @Test
    public void testGetPossiblePlays(){
        Match match = Mockito.mock(Match.class);
        for (Piece piece: pieces)
            Mockito.doReturn(new HashSet<>()).when(piece).getPossiblePlays(match);
        player.getPossiblePlays(match);
        for (Piece piece: pieces)
            Mockito.verify(piece).getPossiblePlays(match);
    }

    @Test
    public void testEquals() {
        Player player1 = new User(600, true);
        Player player2 = new User(600,true);
        Assertions.assertEquals(player1,player2);

        player2.addPiece(new Pawn(new Position(0, 0), true));
        Assertions.assertFalse(player1.equals(player2));
    }
}

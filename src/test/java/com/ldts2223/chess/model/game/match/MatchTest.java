package com.ldts2223.chess.model.game.match;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;

public class MatchTest {

    private Match match;

    @BeforeEach
    public void setup(){
        match = Mockito.spy(new MatchBuilder().buildMatch(0));
    }

    @Test
    public void selectPiece(){
        Piece piece = new Pawn(new Position(1,2), false);
        match.selectPiece(piece);

        Assertions.assertSame(piece, match.getSelectedPiece());
        Assertions.assertTrue(piece.isSelected());
    }

    @Test
    public void getIdlePlayer(){
        Assertions.assertSame(match.getBlackPlayer(), match.getIdlePlayer());
    }

    @Test
    public void getActivePlayer(){
        Assertions.assertSame(match.getWhitePlayer(), match.getActivePlayer());
    }

    @Test
    public void getPlayIn(){
        Piece piece = new Pawn(new Position(1,2), false);
        Play play1 = new Move(new Position(2,1), piece);
        Play play2 = new Move(new Position(2,2), piece);
        match.setPlays(new HashSet<>(Arrays.asList(play1, play2)));

        Assertions.assertSame(play1, match.getPlayIn(new Position(2,1)));
        Assertions.assertSame(play2, match.getPlayIn(new Position(2,2)));
        Assertions.assertNull(match.getPlayIn(new Position(2,3)));
    }

    @Test
    public void getPieceIn(){
        Piece piece1 = new Pawn(new Position(2,2), false);
        Piece piece2 = new Pawn(new Position(2,7), true);

        Assertions.assertEquals(piece1, match.getPieceIn(new Position(2,2)));
        Assertions.assertEquals(piece2, match.getPieceIn(new Position(2,7)));
        Assertions.assertNull(match.getPieceIn(new Position(4, 4)));
    }

    @Test
    public void emptyPlays(){
        match.emptyPlays();
        Assertions.assertEquals(0, match.getPlays().size());
    }

    @Test
    public void switchTurn(){
        Player firstPlayer = match.getActivePlayer();
        Player secondPlayer = match.getIdlePlayer();

        match.switchTurn(0);

        Assertions.assertSame(secondPlayer, match.getActivePlayer());
        Assertions.assertSame(firstPlayer, match.getIdlePlayer());
        Assertions.assertNotSame(firstPlayer, secondPlayer);
    }

    @Test
    public void check(){
        Mockito.doReturn(true).when(match).avoidCheck(Mockito.any(Play.class));
        Assertions.assertFalse(match.check());

        Mockito.doReturn(false).when(match).avoidCheck(Mockito.any(Play.class));
        Assertions.assertTrue(match.check());
    }

    @Test
    public void isTimeOver(){
        match.getActivePlayer().setTime(0);
        Assertions.assertTrue(match.isTimeOver());
        match.getActivePlayer().setTime(10);
        Assertions.assertFalse(match.isTimeOver());
    }

    @Test
    public void filterCheckPlays(){
        Play play1 = new Move(new Position(2,3), new Pawn(new Position(2,2), false));
        Play play2 = new Move(new Position(5,3), new Pawn(new Position(5,2), false));
        HashSet<Play> plays = new HashSet<>(Arrays.asList(play1, play2));

        HashSet<Play> expected = new HashSet<>(Arrays.asList(play2));

        Mockito.doReturn(false).when(match).avoidCheck(play1);
        Mockito.doReturn(true).when(match).avoidCheck(play2);

        HashSet<Play> filtered = match.filterCheckPlays(plays);

        Assertions.assertEquals(expected, filtered);
    }

    @Test
    public void avoidCheckFalse() {
        Play play = Mockito.mock(Play.class);

        Play opponentPlay1 = Mockito.mock(Play.class);
        Play opponentPlay2 = Mockito.mock(Play.class);
        Match opponentSimulation1 = Mockito.mock(Match.class);
        Match opponentSimulation2 = Mockito.mock(Match.class);
        HashSet<Play> opponentPlays = new HashSet<>(Arrays.asList(opponentPlay1, opponentPlay2));

        Match simulation = Mockito.mock(Match.class);
        Player player = Mockito.mock(Player.class);

        Mockito.doReturn(simulation).when(play).simulate(match);
        Mockito.doReturn(player).when(simulation).getActivePlayer();
        Mockito.doReturn(opponentPlays).when(player).getPossiblePlays(simulation);
        Mockito.doReturn(opponentSimulation1).when(opponentPlay1).simulate(simulation);
        Mockito.doReturn(opponentSimulation2).when(opponentPlay2).simulate(simulation);

        Mockito.doReturn(player).when(opponentSimulation1).getActivePlayer();
        Mockito.doReturn(player).when(opponentSimulation2).getActivePlayer();
        Mockito.doReturn(Mockito.mock(King.class)).when(player).getKing();
        Mockito.doReturn(false).when(opponentSimulation1).isAlive(Mockito.any(King.class));
        Mockito.doReturn(true).when(opponentSimulation2).isAlive(Mockito.any(King.class));

        Assertions.assertFalse(match.avoidCheck(play));
    }

    @Test
    public void avoidCheckTrue() {
        Play play = Mockito.mock(Play.class);

        Play opponentPlay1 = Mockito.mock(Play.class);
        Play opponentPlay2 = Mockito.mock(Play.class);
        Match opponentSimulation1 = Mockito.mock(Match.class);
        Match opponentSimulation2 = Mockito.mock(Match.class);
        HashSet<Play> opponentPlays = new HashSet<>(Arrays.asList(opponentPlay1, opponentPlay2));

        Match simulation = Mockito.mock(Match.class);
        Player player = Mockito.mock(Player.class);

        Mockito.doReturn(simulation).when(play).simulate(match);
        Mockito.doReturn(player).when(simulation).getActivePlayer();
        Mockito.doReturn(opponentPlays).when(player).getPossiblePlays(simulation);
        Mockito.doReturn(opponentSimulation1).when(opponentPlay1).simulate(simulation);
        Mockito.doReturn(opponentSimulation2).when(opponentPlay2).simulate(simulation);

        Mockito.doReturn(player).when(opponentSimulation1).getActivePlayer();
        Mockito.doReturn(player).when(opponentSimulation2).getActivePlayer();
        Mockito.doReturn(Mockito.mock(King.class)).when(player).getKing();
        Mockito.doReturn(true).when(opponentSimulation1).isAlive(Mockito.any(King.class));
        Mockito.doReturn(true).when(opponentSimulation2).isAlive(Mockito.any(King.class));

        Assertions.assertTrue(match.avoidCheck(play));
    }

    @Test
    public void isAlive(){
        Piece piece1 = new Pawn(new Position(3,7), true);
        Piece piece2 = new Pawn(new Position(2,7), true);
        Piece piece3 = new Pawn(new Position(3,2), false);
        Piece piece4 = new Pawn(new Position(2,2), false);

        match.getWhitePlayer().setPieces(new HashSet<>(Arrays.asList(piece1, piece2)));
        match.getBlackPlayer().setPieces(new HashSet<>(Arrays.asList(piece3)));

        Assertions.assertTrue(match.isAlive(piece1));
        Assertions.assertTrue(match.isAlive(piece3));
        Assertions.assertFalse(match.isAlive(piece4));
    }

    @Test
    public void isCheckMateTrue(){
        Mockito.doReturn(new HashSet<Play>()).when(match).filterCheckPlays(Mockito.any());
        Mockito.doReturn(true).when(match).check();

        Assertions.assertTrue(match.isCheckMate());
    }

    @Test
    public void isCheckMateFalse(){
        Mockito.doReturn(new HashSet<Play>()).when(match).filterCheckPlays(Mockito.any());
        Mockito.doReturn(false).when(match).check();

        Assertions.assertFalse(match.isCheckMate());

        Mockito.doReturn(new HashSet<Play>(Arrays.asList(Mockito.mock(Play.class))))
                .when(match).filterCheckPlays(Mockito.any());

        Mockito.doReturn(true).when(match).check();

        Assertions.assertFalse(match.isCheckMate());
    }

    @Test
    public void isStaleMateTrue(){
        Mockito.doReturn(new HashSet<Play>()).when(match).filterCheckPlays(Mockito.any());
        Mockito.doReturn(false).when(match).check();

        Assertions.assertTrue(match.isStaleMate());
    }

    @Test
    public void isStaleMateFalse(){
        Mockito.doReturn(new HashSet<Play>()).when(match).filterCheckPlays(Mockito.any());
        Mockito.doReturn(true).when(match).check();

        Assertions.assertFalse(match.isStaleMate());

        Mockito.doReturn(new HashSet<>(Arrays.asList(Mockito.mock(Play.class))))
                .when(match).filterCheckPlays(Mockito.any());

        Mockito.doReturn(false).when(match).check();

        Assertions.assertFalse(match.isStaleMate());
    }

    @Test
    public void cloneTest(){
        match.switchTurn(0);
        Match clone = match.clone();

        for (Piece piece: match.getBlackPlayer().getPieces())
            Assertions.assertNotNull(clone.getPieceIn(piece.getPosition()));

        for (Piece piece: match.getWhitePlayer().getPieces())
            Assertions.assertNotNull(clone.getPieceIn(piece.getPosition()));

        Assertions.assertEquals(match.getActivePlayer().isWhite(), clone.getActivePlayer().isWhite());
    }
}

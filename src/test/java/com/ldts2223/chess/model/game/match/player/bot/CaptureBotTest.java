package com.ldts2223.chess.model.game.match.player.bot;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.EnPassant;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;

public class CaptureBotTest {

    private Play move1, move2, enPassant, capture;
    private Bot bot;

    @BeforeEach
    public void setUp(){
        move1 = new Move(
                new Position(1,1),
                new Pawn(new Position(1,2), false));

        move2 = new Move(
                new Position(2,1),
                new Pawn(new Position(2,2), false));

        enPassant = new EnPassant(new Position(1,1),
                new Pawn(new Position(1,2), false),
                new Pawn(new Position(1,2), false));

        capture = new Capture(
                new Position(1,1),
                new Pawn(new Position(1,2), false),
                new Pawn(new Position(1,2), false));

        bot =  Mockito.spy(new CaptureBot(0, false));
    }

    @Test
    public void selectNotPriorityPlay() {
        Match match = Mockito.mock(Match.class);
        HashSet<Play> plays = new HashSet<>(Arrays.asList(move1, move2));
        Mockito.when(bot.getPossiblePlays(match)).thenReturn(plays);

        Mockito.when(match.filterCheckPlays(plays)).thenReturn(plays);

        Play selectedPlay = bot.selectPlay(match);

        Assertions.assertTrue(move1 == selectedPlay || move2 == selectedPlay);
    }

    @Test
    public void selectPriorityPlay() {
        Match match = Mockito.mock(Match.class);
        HashSet<Play> plays = new HashSet<>(Arrays.asList(move1, move2, capture, enPassant));
        Mockito.when(bot.getPossiblePlays(match)).thenReturn(plays);
        Mockito.when(match.filterCheckPlays(plays)).thenReturn(plays);

        Play selectedPlay = bot.selectPlay(match);

        Assertions.assertTrue(capture == selectedPlay || enPassant == selectedPlay);
    }

    @Test
    public void testClone() {
        Player player = new CaptureBot(600, true);

        Pawn pawn = new Pawn(new Position(2,6), true);
        King king = new King(new Position(7,6), true);

        player.addPiece(pawn);
        player.addPiece(king);

        Player clone = player.clone();

        Assertions.assertNotSame(player, clone);
        Assertions.assertEquals(player.getTime(), clone.getTime());
        Assertions.assertEquals(player.isWhite(), clone.isWhite());
        Assertions.assertEquals(player.getPieces().size(), clone.getPieces().size());
        Assertions.assertNotSame(player.getKing(), clone.getKing());
    }
}


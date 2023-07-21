package com.ldts2223.chess.model.game.match.player.bot;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.*;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.plays.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;

public class RandomBotTest {

    @Test
    public void selectPlay() {
        Play enPassant = new EnPassant(new Position(1,1),
                new Pawn(new Position(1,2), false),
                new Pawn(new Position(1,2), false));

        Play capture = new Capture(
                new Position(1,1),
                new Pawn(new Position(1,2), false),
                new Pawn(new Position(1,2), false));

        Play move = new Move(
                new Position(1,1),
                new Pawn(new Position(1,2), false));

        Bot bot =  Mockito.spy(new RandomBot(0, false));
        
        HashSet<Play> plays = new HashSet<>(Arrays.asList(capture, move));
        Mockito.when(bot.getPossiblePlays(Mockito.any(Match.class))).thenReturn(plays);

        Match match = Mockito.mock(Match.class);
        Mockito.when(match.filterCheckPlays(plays)).thenReturn(plays);
        Play selectedPlay = bot.selectPlay(match);

        Assertions.assertTrue(capture == selectedPlay || enPassant == selectedPlay || move == selectedPlay);
    }

    @Test
    public void testClone() {
        Player player = new RandomBot(600, false);

        Horse horse = new Horse(new Position(1,8), false);
        King king = new King(new Position(7,6), false);
        Queen queen = new Queen(new Position(5,2), false);

        player.addPiece(horse);
        player.addPiece(king);
        player.addPiece(queen);

        Player clone = player.clone();

        Assertions.assertNotSame(player, clone);
        Assertions.assertEquals(player.getTime(), clone.getTime());
        Assertions.assertEquals(player.isWhite(), clone.isWhite());
        Assertions.assertEquals(player.getPieces().size(), clone.getPieces().size());
        Assertions.assertNotSame(player.getKing(), clone.getKing());
    }
}

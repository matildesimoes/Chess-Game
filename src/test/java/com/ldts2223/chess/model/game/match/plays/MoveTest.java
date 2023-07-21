package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;

public class MoveTest {

    private Position destination;
    private Pawn selectedPiece;
    private Promotion promotion;
    private Move move;

    @BeforeEach
    void setUp() {
        destination = new Position(2, 2);
        selectedPiece = new Pawn(new Position(3, 3), false);
        promotion = new Promotion(destination,selectedPiece);
    }

    @Test
    public void testMoveWithoutPromotion(){
        move = new Move(destination,selectedPiece);

        Assertions.assertEquals(destination,move.getDestination());
        Assertions.assertEquals(selectedPiece,move.getSelectedPiece());
        Assertions.assertEquals("#32CD32",move.getColor());
    }

    @Test
    public void testMoveWithPromotion(){
       move = new Move(destination,selectedPiece,promotion);

        Assertions.assertEquals(destination,move.getDestination());
        Assertions.assertEquals(selectedPiece,move.getSelectedPiece());
        Assertions.assertEquals(promotion.getColor(),move.getColor());
    }

    @Test
    public void execute(){

        Match match = new MatchBuilder().buildMatch(0);
        match.getActivePlayer().setPieces(new HashSet<>(Arrays.asList(selectedPiece)));
        match.getIdlePlayer().setPieces(new HashSet<>());
        Move move = new Move(destination, selectedPiece);

        move.furtherExecute(match);

        Assertions.assertEquals(destination, selectedPiece.getPosition());
    }

    @Test
    public void testClone() {
        Match match = Mockito.mock(Match.class);
        Piece piece = Mockito.mock(Piece.class);

        Mockito.when(piece.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(match.getPieceIn(new Position(0, 0))).thenReturn(piece);

        Move move = new Move(new Position(1, 1), piece);
        Play clone = move.clone(match);

        Move cloneMove = (Move) clone;
        Assertions.assertEquals(new Position(1, 1), cloneMove.getDestination());
    }
}

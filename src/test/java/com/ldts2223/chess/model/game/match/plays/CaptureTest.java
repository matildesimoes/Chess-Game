package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.HashSet;
import static org.mockito.Mockito.*;

public class CaptureTest {

    private Position destination;
    private Pawn selectedPiece;
    private Rook captured;
    private Promotion promotion;

    @BeforeEach
    void setUp() {
        destination = new Position(2, 2);
        selectedPiece = new Pawn(new Position(3, 3), false);
        captured = new Rook(new Position(2, 2), true);
        promotion = new Promotion(destination,selectedPiece);
    }

    @Test
    public void testCapturedWithoutPromotion(){
        Capture capture = new Capture(destination,selectedPiece,captured);

        Assertions.assertEquals(destination,capture.getDestination());
        Assertions.assertEquals(selectedPiece,capture.getSelectedPiece());
        Assertions.assertEquals("#FF0000",capture.getColor());
        Assertions.assertNotNull(capture.getImage());
    }

    @Test
    public void testCaptureWithPromotion(){
        Capture capture = new Capture(destination,selectedPiece,captured,promotion);

        Assertions.assertEquals(destination,capture.getDestination());
        Assertions.assertEquals(selectedPiece,capture.getSelectedPiece());
        Assertions.assertEquals(promotion.getColor(),capture.getColor());
        Assertions.assertNotNull(capture.getImage());
    }

    @Test
    public void execute(){
        Match match = new MatchBuilder().buildMatch(0);
        match.getActivePlayer().setPieces(new HashSet<>(Arrays.asList(selectedPiece)));
        match.getIdlePlayer().setPieces(new HashSet<>(Arrays.asList(captured)));
        Capture capture = new Capture(destination, selectedPiece, captured);

        capture.furtherExecute(match);

        Assertions.assertEquals(0, match.getIdlePlayer().getPieces().size());
        Assertions.assertEquals(destination, selectedPiece.getPosition());
    }

    @Test
    public void testClone() {
        Match match = mock(Match.class);
        Piece piece = mock(Piece.class);
        Piece captured = mock(Piece.class);

        Mockito.when(piece.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(captured.getPosition()).thenReturn(new Position(1, 1));

        Mockito.when(match.getPieceIn(new Position(0, 0))).thenReturn(piece);
        Mockito.when(match.getPieceIn(new Position(1, 1))).thenReturn(captured);

        Capture capture = new Capture(new Position(1, 1), piece, captured);
        Play clone = capture.clone(match);

        Capture cloneCapture = (Capture) clone;
        Assertions.assertEquals(new Position(1, 1), cloneCapture.getDestination());
    }
}

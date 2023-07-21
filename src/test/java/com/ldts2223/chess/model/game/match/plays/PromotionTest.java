package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.*;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;

import static org.mockito.Mockito.mock;

public class PromotionTest {

    private Match match;
    private Player player = new User(600,true);
    private Position destination;
    private Pawn selectedPiece;
    private Promotion promotion;

    @BeforeEach
    void helper(){
        match = new Match(600);
        player = match.getActivePlayer();
        destination = new Position(6,8);
        selectedPiece = new Pawn(new Position(5,7),true);
        promotion = new Promotion(destination,selectedPiece);
    }

    @Test
    void testConstructor(){
        Assertions.assertEquals(destination,promotion.getDestination());
        Assertions.assertEquals(selectedPiece,promotion.getSelectedPiece());
        Assertions.assertEquals("#ffa600",promotion.getColor());
        Assertions.assertEquals(promotion.getPosition(),promotion.getPromotionPiece().getPosition());
    }

    @Test
    public void testExec(){
        match = mock(Match.class);
        player = mock(Player.class);
        Mockito.when(match.getActivePlayer()).thenReturn(player);

        HashSet<Piece> pieces = new HashSet<>();
        pieces.add(new Bishop(new Position(2,3), true));
        pieces.add(new King(new Position(4,7), true));
        pieces.add(new Pawn(new Position(2,8), true));

        Mockito.when(player.getPieces()).thenReturn(pieces);

        promotion.furtherExecute(match);

        Mockito.verify(player).losePiece(selectedPiece.getPosition());
        Mockito.verify(player).addPiece(promotion.getPromotionPiece());
    }

    @Test
    public void testEquals() {
        Position destination1 = new Position(6, 8);
        Piece selectedPiece1 = new Pawn(new Position(5, 7), true);
        Promotion promotion1 = new Promotion(destination1, selectedPiece1);

        Position destination2 = new Position(7, 8);
        Piece selectedPiece2 = new Pawn(new Position(5, 7), true);
        Promotion promotion2 = new Promotion(destination2, selectedPiece2);

        Assertions.assertTrue(promotion.equals(promotion1));
        Assertions.assertFalse(promotion.equals(promotion2));
    }

    @Test
    public void testHashCode() {
        Position destination1 = new Position(6, 8);
        Pawn selectedPiece1 = new Pawn(new Position(5, 7), true);
        Promotion promotion1 = new Promotion(destination1, selectedPiece1);

        Assertions.assertEquals(promotion.hashCode(), promotion1.hashCode());
    }

    @Test
    public void testClone() {
        Promotion clonedPromotion = (Promotion) promotion.clone(match);

        Assertions.assertEquals(promotion, clonedPromotion);
    }
}

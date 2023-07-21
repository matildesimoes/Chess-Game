package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.Queen;
import com.ldts2223.chess.model.game.match.player.Player;
import java.util.*;

public class Promotion extends Play{

    private Piece promotionPiece;

    public Promotion(Position destination, Piece selectedPiece) {
        super(destination, selectedPiece);
        this.setColor("#ffa600");
        promotionPiece = new Queen(destination, selectedPiece.isWhite());
    }

    @Override
    public Position getPosition(){
        return getPromotionPiece().getPosition();
    }

    public Piece getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    protected void furtherExecute(Match match){
        Player player = match.getActivePlayer();
        player.losePiece(getSelectedPiece().getPosition());
        player.addPiece(promotionPiece);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Promotion promotion)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getPromotionPiece(), promotion.getPromotionPiece());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPromotionPiece());
    }

    @Override
    protected Play clone(Match simulation) {
        return new Promotion(getDestination(), getSelectedPiece());
    }
}

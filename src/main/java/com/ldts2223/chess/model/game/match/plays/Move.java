package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.Pawn;

import static java.lang.Math.abs;

public class Move extends Play{

    private Promotion promotion;

    public Move(Position destination, Piece selectedPiece){
        super(destination, selectedPiece);
        setColor("#32CD32");
    }

    public Move(Position destination, Piece selectedPiece, Promotion promotion){
        super(destination, selectedPiece);
        this.setColor(promotion.getColor());
        this.promotion = promotion;
    }

    @Override
    protected void furtherExecute(Match match){
        if (getSelectedPiece() instanceof Pawn)
            if (abs(getDestination().getY() - getSelectedPiece().getPosition().getY()) == 2)
                match.setPawnThatDoubled(getSelectedPiece());
        getSelectedPiece().setHasMoved(true);
        getSelectedPiece().setPosition(getDestination());

        if (promotion != null)
            promotion.furtherExecute(match);
    }

    protected Play clone(Match simulation){
        Piece piece = simulation.getPieceIn(getSelectedPiece().getPosition());
        return new Move(getDestination().clone(), piece);
    }
}

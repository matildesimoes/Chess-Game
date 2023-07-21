package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;

public class Capture extends Move{

    protected Piece captured;

    public Capture(Position destination, Piece selectedPiece, Piece captured){
        super(destination, selectedPiece);
        this.captured = captured;
        this.setColor("#FF0000");
        this.setImage(captured.getImage());
    }
    public Capture(Position destination, Piece selectedPiece, Piece captured, Promotion promotion){
        super(destination, selectedPiece, promotion);
        this.captured = captured;
        this.setColor(promotion.getColor());
        this.setImage(captured.getImage());
    }

    @Override
    protected void furtherExecute(Match match){
        match.getIdlePlayer().losePiece(captured.getPosition());
        super.furtherExecute(match);
    }

    @Override
    protected Play clone(Match simulation) {
        Piece piece = simulation.getPieceIn(getSelectedPiece().getPosition());
        Piece captured = simulation.getPieceIn(this.captured.getPosition());
        return new Capture(getDestination(), piece, captured);
    }
}

package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.Pawn;

public class EnPassant extends Capture{

    public EnPassant(Position destination, Piece selectedPiece, Piece captured) {
        super(destination, selectedPiece, captured);
        this.setColor("#FF8C00");
        this.setImage(' ');
    }

    @Override
    protected void furtherExecute(Match match){
        super.furtherExecute(match);
    }

    protected Play clone(Match simulation){
        Piece piece = simulation.getPieceIn(getSelectedPiece().getPosition());
        Pawn pawn = (Pawn) simulation.getPieceIn(captured.getPosition());
        return new EnPassant(this.getDestination(), piece, pawn);
    }
}

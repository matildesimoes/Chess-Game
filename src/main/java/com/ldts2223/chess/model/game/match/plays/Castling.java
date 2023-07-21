package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.Rook;

public class Castling extends Play{

    private Rook rook;
    protected boolean rookRight;

    public Castling(Position destination, Piece selectedPiece, Piece rook, boolean rookRight){
        super(destination, selectedPiece);
        this.rook = (Rook)rook;
        this.setColor("#FF8C00");
        this.rookRight = rookRight;
    }

    @Override
    protected void furtherExecute(Match match){
        getSelectedPiece().setHasMoved(true);
        rook.setHasMoved(true);
        getSelectedPiece().setPosition(getDestination());

        Position rookPos = new Position(getDestination().getX(), getDestination().getY());
        rookPos.setX(getDestination().getX() + (rookRight ? -1 : 1));
        rook.setPosition(rookPos);
    }

    protected Play clone(Match simulation){
        Piece piece = simulation.getPieceIn(getSelectedPiece().getPosition());
        Rook rook = (Rook) simulation.getPieceIn(this.rook.getPosition());
        return new Castling(this.getDestination(), piece, rook, this.rookRight);
    }
}

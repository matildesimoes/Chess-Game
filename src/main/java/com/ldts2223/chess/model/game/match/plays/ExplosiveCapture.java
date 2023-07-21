package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;

public class ExplosiveCapture extends Capture{

    public ExplosiveCapture(Position destination, Piece selectedPiece, Piece captured) {
        super(destination, selectedPiece, captured);
    }

    @Override
    public void furtherExecute(Match match){
        super.furtherExecute(match);
        explodePiece(match);
    }

    private void explodePiece(Match match) {
        for (int x = -1; x <= 1; x++){
            for (int y = -1; y <= 1; y++) {
                Position position = new Position(getDestination().getX() + x, getDestination().getY() + y);
                match.getWhitePlayer().losePiece(position);
                match.getBlackPlayer().losePiece(position);
            }
        }
    }

    @Override
    protected Play clone(Match simulation) {
        Piece piece = simulation.getPieceIn(getSelectedPiece().getPosition());
        Piece captured = simulation.getPieceIn(this.captured.getPosition());
        return new ExplosiveCapture(getDestination(), piece, captured);
    }
}

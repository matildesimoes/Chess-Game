package com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.Promotion;

public class DefaultCaptureEngine implements CaptureEngine {
    public Capture generateCapture(Position destination, Piece selectedPiece, Piece captured, Promotion promotion){
        if (promotion != null)
            return new Capture(destination, selectedPiece, captured, promotion);
        return new Capture(destination, selectedPiece, captured);
    }
}

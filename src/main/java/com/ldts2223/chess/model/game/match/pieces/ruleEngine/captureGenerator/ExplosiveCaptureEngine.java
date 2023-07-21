package com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.ExplosiveCapture;
import com.ldts2223.chess.model.game.match.plays.Promotion;

public class ExplosiveCaptureEngine implements CaptureEngine {
    public Capture generateCapture(Position destination, Piece selectedPiece, Piece captured, Promotion promotion){
        return new ExplosiveCapture(destination, selectedPiece, captured);
    }
}

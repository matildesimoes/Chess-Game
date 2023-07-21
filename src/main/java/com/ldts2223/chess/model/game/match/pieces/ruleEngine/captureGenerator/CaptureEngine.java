package com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.Promotion;

public interface CaptureEngine {
    Capture generateCapture(Position destination, Piece selectedPiece, Piece captured, Promotion promotion);
}

package com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements;

import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.CaptureEngine;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.HashSet;

public interface Movement {
    HashSet<Play> getPossiblePlays(Match match, Piece piece, CaptureEngine captureEngine);
}

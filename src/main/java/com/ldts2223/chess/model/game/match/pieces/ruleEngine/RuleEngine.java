package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.CaptureEngine;
import com.ldts2223.chess.model.game.match.plays.Play;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.DefaultCaptureEngine;

import java.util.HashSet;

public abstract class RuleEngine {

    private Piece piece;
    private CaptureEngine captureEngine = new DefaultCaptureEngine();
    private HashSet<Movement> movements = new HashSet<>();

    public HashSet<Movement> getMovements() {
        return movements;
    }

    public void setMovements(HashSet<Movement> movements) {
        this.movements = movements;
    }

    public void addMovement(Movement movement) {
        this.movements.add(movement);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setCaptureGenerator(CaptureEngine captureEngine) {
        this.captureEngine = captureEngine;
    }

    public CaptureEngine getCaptureGenerator() {
        return captureEngine;
    }

    public HashSet<Play> getPossiblePlays(Match match, Piece piece){
        HashSet<Play> plays = new HashSet<>();
        for (Movement movement: movements)
            plays.addAll(movement.getPossiblePlays(match, piece, captureEngine));

        return plays;
    }

    public abstract RuleEngine clone();
}

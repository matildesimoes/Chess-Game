package com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Queen;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.ExplosiveCapture;
import com.ldts2223.chess.model.game.match.plays.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExplosiveCaptureEngineTest {

    private Promotion promotion;
    private ExplosiveCaptureEngine explosiveCaptureEngine;
    private Capture result;

    @Test
    void test(){
        explosiveCaptureEngine = new ExplosiveCaptureEngine();
        promotion = new Promotion(new Position(8,1),new Queen(new Position(8,1),true));

        result = explosiveCaptureEngine.generateCapture(new Position(8,1),
                new Queen(new Position(8,1),true),
                new Pawn(new Position(8,2),true),
                promotion);

        Assertions.assertEquals(result, new ExplosiveCapture(new Position(8,1),
                    new Queen(new Position(8,1),true),
                    new Pawn(new Position(8,2),true)));
    }
}

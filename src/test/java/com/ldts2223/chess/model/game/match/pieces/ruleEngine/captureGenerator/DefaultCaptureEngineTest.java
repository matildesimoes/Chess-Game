package com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Queen;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultCaptureEngineTest {

    private Promotion promotionNull;
    private Promotion promotion;
    private DefaultCaptureEngine defaultCaptureEngine;
    private Capture resultNull;
    private Capture result;

    @Test
    void test(){
        defaultCaptureEngine = new DefaultCaptureEngine();
        promotionNull = null;
        promotion = new Promotion(new Position(8,1),new Queen(new Position(8,1),true));

        result = defaultCaptureEngine.generateCapture(new Position(8,1),
                new Queen(new Position(8,1),true),
                new Pawn(new Position(8,2),true),
                promotion);

        resultNull = defaultCaptureEngine.generateCapture(new Position(8,1),
                new Queen(new Position(8,1),true),
                new Pawn(new Position(8,2),true),
                promotionNull);

        if (promotion != null)
            Assertions.assertEquals(result, new Capture(new Position(8,1),
                    new Queen(new Position(8,1),true),
                    new Pawn(new Position(8,2),true),
                    new Promotion(new Position(8,1), new Queen(new Position(8,1),true))));

        Assertions.assertEquals(resultNull, new Capture(new Position(8,1),
                new Queen(new Position(8,1),true),
                new Pawn(new Position(8,2),true)));
    }
}

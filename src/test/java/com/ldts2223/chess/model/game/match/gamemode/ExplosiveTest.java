package com.ldts2223.chess.model.game.match.gamemode;

import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.ExplosivePawnRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.HorseWithQueenRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ExplosiveTest {

    private Match match;
    private RuleEngine ruleEngine;
    private Player blackPlayer;
    private Player whitePlayer;

    @BeforeEach
    void helper(){
        match = new Match(0);
        match.setBlackPlayer(new User(0, false));
        match.setWhitePlayer(new User(0, true));
        new Explosive().load(match);

        blackPlayer = match.getBlackPlayer();
        whitePlayer = match.getWhitePlayer();
    }
    @Test
    void load(){
        Assertions.assertEquals(30, blackPlayer.getPieces().size());
        Assertions.assertEquals(30, whitePlayer.getPieces().size());

        for(Piece piece : blackPlayer.getPieces()){
            if(piece.getImage() == '`'){
                ruleEngine = piece.getRuleEngine();
                Assertions.assertTrue(ruleEngine.getClass() == ExplosivePawnRules.class);
            }
            if(piece.getImage() == '^'){
                ruleEngine = piece.getRuleEngine();
                Assertions.assertTrue(ruleEngine.getClass() == HorseWithQueenRules.class);
            }
        }
    }
}

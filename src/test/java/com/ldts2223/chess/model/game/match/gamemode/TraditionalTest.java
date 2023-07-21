package com.ldts2223.chess.model.game.match.gamemode;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TraditionalTest {

    private Match match;
    private Player blackPlayer;
    private Player whitePlayer;

    @BeforeEach
    void helper(){
        match = new Match(0);
        match.setBlackPlayer(new User(0, false));
        match.setWhitePlayer(new User(0, true));
        new Traditional().load(match);

        blackPlayer = match.getBlackPlayer();
        whitePlayer = match.getWhitePlayer();
    }

    @Test
    void load(){
        Assertions.assertEquals(16, blackPlayer.getPieces().size());
        Assertions.assertEquals(16, whitePlayer.getPieces().size());

        for(Piece piece : blackPlayer.getPieces()){
            if(piece.getImage() == '_'){
                Assertions.assertEquals(new Position(5,1),piece.getPosition());
            }
            if(piece.getImage() == '{'){
                Assertions.assertEquals(new Position(4,1),piece.getPosition());
            }
        }
        for(Piece piece : whitePlayer.getPieces()){
            if(piece.getImage() == '_'){
                Assertions.assertEquals(new Position(5,8),piece.getPosition());
            }
            if(piece.getImage() == '{'){
                Assertions.assertEquals(new Position(4,8),piece.getPosition());
            }
        }
    }
}

package com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.CaptureEngine;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.Arrays;
import java.util.HashSet;

public class DefaultHorseMovement implements Movement {

    private HashSet<Position> directions = new HashSet<>(Arrays.asList(
            new Position(2,1), new Position(2,-1), new Position(-2,1), new Position(-2,-1),
            new Position(1,2), new Position(1,-2), new Position(-1,2), new Position(-1, -2)));

    @Override
    public HashSet<Play> getPossiblePlays(Match match, Piece piece, CaptureEngine captureEngine){
        HashSet<Play> plays = new HashSet<>();

        for (Position direction: directions)
            plays.addAll(getJump(direction, match, piece, captureEngine));

        return plays;
    }

    private HashSet<Play> getJump(Position direction, Match match, Piece piece, CaptureEngine captureEngine){
        HashSet<Play> plays = new HashSet<>();

        int x = piece.getPosition().getX() + direction.getX();
        int y = piece.getPosition().getY() + direction.getY();

        if(x >= 1 && x <= match.getBoard().getSize()){
            if(1 <= y && y <= match.getBoard().getSize()){
                Position position = new Position(x,y);
                Piece captured = match.getPieceIn(position);
                if(captured != null) {
                    if (captured.getColor() == piece.getColor())
                        return plays;
                    else
                        plays.add(captureEngine.generateCapture(position, piece, captured, null));
                }
                else
                    plays.add(new Move(position, piece));
            }
        }
        return plays;
    }
}

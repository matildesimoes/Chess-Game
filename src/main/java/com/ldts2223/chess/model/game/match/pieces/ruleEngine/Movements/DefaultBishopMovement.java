package com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.CaptureEngine;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.Arrays;
import java.util.HashSet;

public class DefaultBishopMovement implements Movement {

    private final Position
            RIGHT_UP = (new Position(1, 1)),
            LEFT_UP = (new Position(-1, 1)),
            RIGHT_DOWN = (new Position(1, -1)),
            LEFT_DOWN = (new Position(-1, -1));
    
    private HashSet<Position> directions = new HashSet<>(Arrays.asList(
            RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN));

    public HashSet<Play> getPossiblePlays(Match match, Piece piece, CaptureEngine captureEngine){
        HashSet<Play> plays = new HashSet<>();

        for (Position direction: directions)
            plays.addAll(getPlaysInDirection(direction, match, piece, captureEngine));

        return plays;
    }

    private HashSet<Play> getPlaysInDirection(Position direction, Match match, Piece piece, CaptureEngine captureEngine){
        HashSet<Play> plays = new HashSet<>();

        int x = piece.getPosition().getX() + 1 * direction.getX();
        int y = piece.getPosition().getY() + 1 * direction.getY();

        while( 1 <= x && x <= match.getBoard().getSize() && 1 <= y && y <= match.getBoard().getSize()) {

            Position position = new Position(x,y);
            Piece captured = match.getPieceIn(position);
            if(captured != null)
                if (captured.isWhite() == piece.isWhite())
                    return plays;
                else {
                    plays.add(captureEngine.generateCapture(position, piece, captured, null));
                    return plays;
                }
            else
                plays.add(new Move(position,piece));

            x += 1 * direction.getX(); y += 1 * direction.getY();
        }
        return plays;
    }
}

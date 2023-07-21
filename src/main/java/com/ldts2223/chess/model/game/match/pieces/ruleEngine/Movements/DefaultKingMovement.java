package com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.Rook;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.CaptureEngine;
import com.ldts2223.chess.model.game.match.plays.Castling;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.Arrays;
import java.util.HashSet;

public class DefaultKingMovement implements Movement {

    private final Position
            UP = (new Position(0, -1)),
            LEFT = (new Position(-1, 0)),
            RIGHT = (new Position(1, 0)),
            DOWN = (new Position(0, 1)),
            RIGHT_UP = (new Position(1, 1)),
            LEFT_UP = (new Position(-1, 1)),
            RIGHT_DOWN = (new Position(1, -1)),
            LEFT_DOWN = (new Position(-1, -1));

    private HashSet<Position> directions = new HashSet<>(Arrays.asList(
            RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN, UP, LEFT, RIGHT, DOWN));

    @Override
    public HashSet<Play> getPossiblePlays(Match match, Piece piece, CaptureEngine captureEngine) {
        HashSet<Play> plays = new HashSet<>();

        for (Position direction: directions)
            plays.addAll(getPlaysInLine(direction, match, piece, captureEngine));
        plays.addAll(getCastling(match, true, piece));
        plays.addAll(getCastling(match, false, piece));

        return plays;
    }

    private HashSet<Play> getPlaysInLine(Position direction, Match match, Piece piece, CaptureEngine captureEngine) {
        HashSet<Play> plays = new HashSet<>();

        int x = piece.getPosition().getX() + direction.getX();
        int y = piece.getPosition().getY() + direction.getY();

        Position destination = new Position(x, y);
        Piece captured = match.getPieceIn(destination);

        if(x >= 1 && x <= match.getBoard().getSize()) {
            if (1 <= y && y <= match.getBoard().getSize()) {

                if (captured != null) {
                    if (captured.getColor() != piece.getColor())
                        plays.add(captureEngine.generateCapture(destination, piece, captured, null));
                    else
                        return plays;
                }
                else
                    plays.add(new Move(destination, piece));
            }
        }
        return plays;
    }

    private HashSet<Play> getCastling(Match match, boolean rookRight, Piece piece) {
        HashSet<Play> plays = new HashSet<>();

        if (piece.hasMoved()) {
            return plays;
        }

        Position rookPos = new Position(piece.getPosition().getX() + (rookRight ? 3 : -4) ,
                piece.getPosition().getY());
        Piece rook = match.getPieceIn(rookPos);

        if (rook instanceof Rook)
            if (!rook.hasMoved()) {
                if (casesEmptyAndFree(rook, match, rookRight, piece)) {
                    int x = piece.getPosition().getX();
                    int y = piece.getPosition().getY();
                    Position destination = new Position(x + (rookRight ? 2 : -2), y);
                    plays.add(new Castling(destination, piece, rook, rookRight));
                }
            }
        return plays;
    }

    private boolean casesEmptyAndFree(Piece rook, Match match, boolean rookRight, Piece piece){
        int x = piece.getPosition().getX() + ( rookRight ? 1 : -1);
        int y = piece.getPosition().getY();
        Position begin = new Position(x, y);

        Position end = rook.getPosition();

        while (!begin.equals(end)){
            if (match.getPieceIn(begin) != null)
                return false;

            if (!match.avoidCheck(new Move(begin, piece)))
                return false;

            begin.setX(begin.getX() + (rookRight ? 1 : -1));
        }
        return true;
    }
}

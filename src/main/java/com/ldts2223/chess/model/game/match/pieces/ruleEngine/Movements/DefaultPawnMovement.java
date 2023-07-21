package com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.CaptureEngine;
import com.ldts2223.chess.model.game.match.plays.EnPassant;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import com.ldts2223.chess.model.game.match.plays.Promotion;

import java.util.HashSet;

public class DefaultPawnMovement implements Movement {

    @Override
    public HashSet<Play> getPossiblePlays(Match match, Piece piece, CaptureEngine captureEngine){
        HashSet<Play> plays = new HashSet<>();

        if(!piece.hasMoved())
            plays.addAll(getPlayForward(2,match, piece));
        else
            plays.addAll(getPlayForward(1,match, piece));

        plays.addAll(getPlayToCapture(1,match, piece, captureEngine));
        plays.addAll(getPlayToCapture(-1,match, piece, captureEngine));
        plays.addAll(getEnPassant(match, piece));

        return plays;
    }

    private HashSet<Play> getPlayForward(int moveY, Match match, Piece piece){
        HashSet<Play> plays = new HashSet<>();

        int x = piece.getPosition().getX();
        int lastRow = piece.isWhite() ? 1 : match.getBoard().getSize();

        for (int i = 1; i <= moveY; i++) {
            if (x >= 1 && x <= match.getBoard().getSize()) {
                int y = piece.getPosition().getY() + i * (piece.isWhite() ? -1 : 1);
                if (1 <= y && y <= match.getBoard().getSize()) {
                    Position destination = new Position(x, y);
                    Piece otherPiece = match.getPieceIn(destination);

                    if (otherPiece != null)
                        return plays;

                    if (y == lastRow)
                        plays.add(new Move(destination, piece, new Promotion(destination, piece)));
                    else
                        plays.add(new Move(destination, piece));
                }
            }
        }
        return plays;
    }

    private HashSet<Play> getPlayToCapture(int moveX, Match match, Piece piece, CaptureEngine captureEngine){
        HashSet<Play> plays = new HashSet<>();

        int x = piece.getPosition().getX() + moveX;
        int y = piece.getPosition().getY() + (piece.isWhite() ? -1 : 1);
        int lastRow = piece.isWhite() ? 1 : match.getBoard().getSize();

        if(x >= 1 && x <= match.getBoard().getSize()) {
            if (1 <= y && y <= match.getBoard().getSize()) {
                Position destination = new Position(x, y);
                Piece captured = match.getPieceIn(destination);
                if (captured != null) {
                    if (captured.getColor() != piece.getColor())
                        if (y == lastRow)
                            plays.add(captureEngine.generateCapture(destination, piece, captured,
                                    new Promotion(destination, piece)));
                        else
                            plays.add(captureEngine.generateCapture(destination, piece, captured, null));
                }
            }
        }
        return plays;
    }

    private HashSet<Play> getEnPassant(Match match, Piece piece){
        HashSet<Play> plays = new HashSet<>();

        for (int side = -1; side <= 1; side +=2) {

            int x = piece.getPosition().getX() + side;
            int y = piece.getPosition().getY();

            Piece captured = match.getPieceIn(new Position(x, y));
            if (captured != null)
                if (captured == match.getPawnThatDoubled() && captured.getColor() != piece.getColor()) {
                    Position destination = new Position(x, y + (piece.isBlack() ? 1 : -1));
                    plays.add(new EnPassant(destination, piece, match.getPawnThatDoubled()));
                }
        }
        return plays;
    }
}

package com.ldts2223.chess.model.game.match.player;

import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.HashSet;

public abstract class Player {

    private long time = 600;
    private long lastMove;
    private boolean isWhite;
    private HashSet<Piece> pieces = new HashSet<>();
    private King king;

    public Player(long initialTime, boolean isWhite){

        this.lastMove = initialTime;
        this.isWhite = isWhite;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public King getKing() {
        return king;
    }

    public HashSet<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(HashSet<Piece> pieces) {
        this.pieces = pieces;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    public void losePiece(Position position){
        HashSet<Piece> updatedPieces = new HashSet<>();
        for (Piece piece: pieces)
            if (!piece.getPosition().equals(position)) {
                updatedPieces.add(piece);
            }
        pieces = updatedPieces;
    }
    public long getLastMove() {
        return lastMove;
    }

    public void updateTime(long currentTime){
        if (currentTime - this.getLastMove() >= 1000){
            time--;
            lastMove = currentTime;
        }
    }

    public void setLastMove(long lastMove) {
        this.lastMove = lastMove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return this.getPieces().equals(player.getPieces());
    }

    public HashSet<Play> getPossiblePlays(Match match) {
        HashSet<Play> plays = new HashSet<>();

        for (Piece piece: pieces){
            plays.addAll(piece.getPossiblePlays(match));
        }
        return plays;
    }

    public abstract Player clone();

    public abstract void play(long time, Match match, Input.ACTION action, Position click);
}

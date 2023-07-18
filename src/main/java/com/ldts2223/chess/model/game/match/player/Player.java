package com.ldts2223.chess.model.game.match.player;

import com.ldts2223.chess.model.game.match.player.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    private List<Piece> pieces = new ArrayList<>();

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return this.getPieces().equals(player.getPieces());
    }
}

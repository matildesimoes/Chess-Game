package com.ldts2223.chess.model.game.match;

import java.util.Objects;

public class Board {

    private int size;
    private final int squareSize = 1;
    private final int boardStart = 2;

    public Board(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public int getBoardStart() {
        return boardStart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return this.size == board.getSize();
    }

    @Override
    public int hashCode() {
       return Objects.hash(size);
    }

    public Board clone(){
        return new Board(size);
    }
}

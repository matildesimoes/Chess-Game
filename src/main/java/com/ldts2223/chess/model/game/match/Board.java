package com.ldts2223.chess.model.game.match;

import com.ldts2223.chess.model.Position;

import java.util.Objects;

public class Board {

    private int width;
    private int height;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return this.width == board.getWidth() && this.height == board.getHeight();
    }

    @Override
    public int hashCode() {
       return Objects.hash(width, height);
    }
}

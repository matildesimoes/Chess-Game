package com.ldts2223.chess.model;

import java.util.Objects;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        boolean x = this.getX() == position.getX();
        return  x && this.getY() == position.getY();
    }

    public Position clone(){
        return new Position(x, y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

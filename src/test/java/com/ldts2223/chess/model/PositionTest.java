package com.ldts2223.chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    private Position position;

    @BeforeEach
    void helper(){
        position = new Position(10, 20 );
    }

    @Test
    void getX(){
        assertEquals(10, position.getX());
    }

    @Test
    void getY(){
        assertEquals(20, position.getY());
    }

    @Test
    void setX(){
        position.setX(15);
        assertEquals(15, position.getX());
    }

    @Test
    void setY(){
        position.setY(25);
        assertEquals(25, position.getY());
    }
}

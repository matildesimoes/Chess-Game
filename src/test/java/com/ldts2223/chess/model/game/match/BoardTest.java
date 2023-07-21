package com.ldts2223.chess.model.game.match;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board1;
    private Board board2;
    private int size;

    @BeforeEach
    void helper(){
        size = 10;
        board1 = new Board(size);
        board2 = new Board(size);
    }

    @Test
    public void testEquals() {
        Assertions.assertEquals(board1, board2);
        board2.setSize(20);
        Assertions.assertNotEquals(board1, board2);
    }

    @Test
    public void testHashCode() {
        Assertions.assertEquals(board1.hashCode(), board2.hashCode());
        board2.setSize(20);
        Assertions.assertNotEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    public void testClone() {
        Board board2 = board1.clone();
        Assertions.assertNotSame(board1, board2);
        Assertions.assertEquals(board1, board2);
    }
}


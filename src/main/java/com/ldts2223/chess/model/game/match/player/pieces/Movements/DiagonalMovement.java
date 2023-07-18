package com.ldts2223.chess.model.game.match.player.pieces.Movements;

import org.w3c.dom.ranges.Range;


public class DiagonalMovement {

    private int numSquares;
    private boolean jump;
    private RESTRAINMENT restrainment;

    private Boolean uprRight;
    private Boolean upLeft;
    private Boolean downRight;
    private Boolean downLeft;

    enum RESTRAINMENT {RANGE, FIXED};

}

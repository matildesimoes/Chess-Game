package com.ldts2223.chess.model.game.match.player.pieces.Movements;

public class StraightMovement {

    private int numSquares;
    private boolean jump;
    private DiagonalMovement.RESTRAINMENT restrainment;

    private Boolean up;
    private Boolean down;
    private Boolean right;
    private Boolean left;

    enum RESTRAINMENT {RANGE, FIXED};
}

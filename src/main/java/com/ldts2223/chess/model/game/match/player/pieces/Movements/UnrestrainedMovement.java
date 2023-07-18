package com.ldts2223.chess.model.game.match.player.pieces.Movements;

public class UnrestrainedMovement {

    private int numSquares;
    private boolean jump;
    private DiagonalMovement.RESTRAINMENT restrainment;

    private Boolean side1;
    private Boolean side2;

    enum RESTRAINMENT {RANGE, FIXED};
}

package com.ldts2223.chess.model.game.match.player.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.player.pieces.Movements.StraightMovement;
import com.ldts2223.chess.model.game.match.player.pieces.Movements.DiagonalMovement;
import com.ldts2223.chess.model.game.match.player.pieces.Movements.UnrestrainedMovement;
import com.ldts2223.chess.model.game.match.player.pieces.perks.Perk;

import java.util.HashSet;
import java.util.Set;

public class Piece {

    private StraightMovement straightMovement = null;
    private DiagonalMovement diagonalMovement = null;
    private UnrestrainedMovement unrestrainedMovement = null;
    private Position position;
    private Set<Perk> perks = new HashSet<>();
    private Character character;

    public Piece(Position position, Character character){
        this.position = position;
        this.character = character;
    }

    public StraightMovement getStraightMovement(){
        return straightMovement;
    }

    public void setStraightMovement(StraightMovement straightMovement) {
        this.straightMovement = straightMovement;
    }

    public DiagonalMovement getDiagonalMovement(){
        return diagonalMovement;
    }

    public void setDiagonalMovement(DiagonalMovement diagonalMovement) {
        this.diagonalMovement = diagonalMovement;
    }

    public UnrestrainedMovement getUnrestrainedMovement() {
        return unrestrainedMovement;
    }

    public void setUnrestrainedMovement(UnrestrainedMovement unrestrainedMovement) {
        this.unrestrainedMovement = unrestrainedMovement;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<Perk> getPerks() {
        return perks;
    }

    public void setPerks(Set<Perk> perks) {
        this.perks = perks;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return this.character.equals(piece.getCharacter()) && this.position.equals(piece.getPosition());

    }
}

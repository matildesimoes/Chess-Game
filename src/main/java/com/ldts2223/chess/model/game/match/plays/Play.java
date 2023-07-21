package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Piece;

import java.util.Objects;

public abstract class Play {

    private Position destination;
    private Piece selectedPiece;
    private String color;
    private Character image = ' ';

    public Play(Position destination, Piece selectedPiece){
        this.destination = destination;
        this.selectedPiece = selectedPiece;
    }

    public Character getImage() {
        return image;
    }

    public void setImage(Character image) {
        this.image = image;
    }

    public Position getDestination() {
        return destination;
    }

    public Position getPosition(){
        return getDestination();
    }

    public void setDestination(Position destination) {
        this.destination = destination;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void execute(Match match){
        match.setPawnThatDoubled(null);
        furtherExecute(match);
        match.emptyPlays();
    }

    public Match simulate(Match match){
        Match simulation = match.clone();
        Play clone = this.clone(simulation);
        clone.execute(simulation);
        simulation.switchTurn(0);
        return simulation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Play play)) return false;
        return Objects.equals(getDestination(), play.getDestination()) &&
                Objects.equals(getSelectedPiece(), play.getSelectedPiece()) &&
                Objects.equals(getColor(), play.getColor());
    }

    abstract protected void furtherExecute(Match match);

    abstract protected Play clone(Match simulation);

    @Override
    public int hashCode() {
        return Objects.hash(getDestination(), getSelectedPiece(), getColor());
    }
}

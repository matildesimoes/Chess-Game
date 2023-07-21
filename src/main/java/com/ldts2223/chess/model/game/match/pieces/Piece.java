package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.HashSet;
import java.util.Objects;

public abstract class Piece {

    private Character image;
    private Position position;
    private boolean isWhite;
    private boolean isSelected;
    private RuleEngine ruleEngine;
    private boolean hasMoved = false;

    public Piece(Position position, boolean isWhite){
        this.position = position;
        this.isWhite = isWhite;
    }

    public RuleEngine getRuleEngine() {
        return ruleEngine;
    }

    public void setRuleEngine(RuleEngine ruleEngine) {
        this.ruleEngine = ruleEngine;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public boolean setHasMoved(boolean hasMove) {
        this.hasMoved = hasMove;
        return hasMove;
    }

    public Character getImage(){
        return image;
    }

    public void setImage(Character image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean getColor(){
        return isWhite;
    }

    public boolean isWhite(){
        return isWhite;
    }

    public boolean isBlack(){
        return !isWhite;
    }

    public void select(){
        isSelected = true;
    }

    public void unselect(){
        isSelected = false;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public HashSet<Play> getPossiblePlays(Match match){
        return ruleEngine.getPossiblePlays(match, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Piece piece)) return false;
        return isWhite() == piece.isWhite() && isSelected() == piece.isSelected() &&
                getImage().equals(piece.getImage()) && getPosition().equals(piece.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImage(), getPosition(), isWhite(), isSelected());
    }

    abstract public Piece clone();
}

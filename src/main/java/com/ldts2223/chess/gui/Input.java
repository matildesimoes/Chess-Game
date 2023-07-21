package com.ldts2223.chess.gui;

import com.ldts2223.chess.model.Position;

public class Input {

    private Position position;
    private ACTION action;

    public Input(Position position, ACTION action){
        this.position = position;
        this.action = action;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setAction(ACTION action) {
        this.action = action;
    }

    public Position getPosition() {
        return position;
    }

    public ACTION getAction() {
        return action;
    }

    public enum ACTION {none, mousebutton1, quit}
}

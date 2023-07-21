package com.ldts2223.chess.controller;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.gui.Input;

public abstract class Controller<T> {

    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    abstract public void step(Game game, Input input, long time) throws InterruptedException;
}
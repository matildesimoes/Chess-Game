package com.ldts2223.chess.controller;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.gui.GUI;

import java.io.IOException;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    //public abstract void step(Main main, GUI.ACTION action, long time) throws IOException;
}
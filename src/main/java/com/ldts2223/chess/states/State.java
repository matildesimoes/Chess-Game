package com.ldts2223.chess.states;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.viewer.Viewer;
import java.io.IOException;

public abstract class State<T> {

    private final T model;
    private final Controller<T> controller;
    private final Viewer<T> viewer;

    public State(T model){
        this.model = model;
        this.controller = getController();
        this.viewer = getViewer();
    }

    public T getModel(){
        return model;
    }

    public void step(Game game, GUI gui, long time) throws IOException, InterruptedException {
        Input input = gui.getNextMouseAction();
        controller.step(game, input, time);
        viewer.draw(gui);
    }

    protected abstract Viewer<T> getViewer();

    protected abstract Controller<T> getController();
}


package com.ldts2223.chess.states;

import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.controller.menu.MenuController;
import com.ldts2223.chess.model.menu.Menu;
import com.ldts2223.chess.viewer.Viewer;
import com.ldts2223.chess.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {

    public MenuState(Menu menu){
        super(menu);
    }
    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}

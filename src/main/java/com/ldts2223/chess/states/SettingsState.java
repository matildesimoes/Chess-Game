package com.ldts2223.chess.states;

import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.controller.settings.SettingsController;
import com.ldts2223.chess.model.game.match.Settings;
import com.ldts2223.chess.viewer.Viewer;
import com.ldts2223.chess.viewer.Settings.SettingsViewer;

public class SettingsState extends State<Settings> {

    public SettingsState(Settings settings){
        super(settings);
    }

    @Override
    protected Viewer<Settings> getViewer() {
        return new SettingsViewer(getModel());
    }

    @Override
    protected Controller<Settings> getController() {
        return new SettingsController(getModel());
    }
}

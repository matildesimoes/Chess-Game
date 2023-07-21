package com.ldts2223.chess.controller.settings;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.game.match.Settings;
import com.ldts2223.chess.model.menu.MainMenu;
import com.ldts2223.chess.states.MenuState;

public class SettingsController extends Controller<Settings> {

    public SettingsController(Settings model) {
        super(model);
    }

    @Override
    public void step(Game game, Input input, long time) {

        if (input.getAction() == Input.ACTION.quit) {
            game.setState(new MenuState(new MainMenu()));
            return;
        }

        int x = input.getPosition().getX();
        int y = input.getPosition().getY();

        if (input.getAction() == Input.ACTION.mousebutton1)
            processClick(x, y, game);
    }

    public void processClick(int x, int y, Game game) {
        Settings settings = getModel();
        if (x == settings.getDecreaseX()) {
            processIncrease(y, settings, settings.getEntryY());
        }
        if (x == settings.getIncreaseX()) {
            processDecrease(y, settings, settings.getEntryY());
        }
        processGoBack(x, y, game, settings);
    }

    private void processGoBack(int x, int y, Game game, Settings settings) {
        int backX = settings.getBackX();
        int backY = settings.getBackY();
        int backEntryLength = settings.getBackEntry().length();
        if (x >= backX && x < backX + backEntryLength && y == backY)
            game.setState(new MenuState(new MainMenu()));
    }

    private void processDecrease(int y, Settings settings, int entryY) {
        if (y == entryY)
            settings.incrementGamemode();
        else if (y == entryY + 1)
            settings.incrementWhitePlayer();
        else if (y == entryY + 2)
            settings.incrementBlackPlayer();
        else if (y == entryY + 3)
            settings.increaseTime();
    }

    private void processIncrease(int y, Settings settings, int entryY) {
        if (y == entryY)
            settings.decrementGamemode();
        else if (y == entryY + 1)
            settings.decrementWhitePlayer();
        else if (y == entryY + 2)
            settings.decrementBlackPlayer();
        else if (y == entryY + 3) {
            settings.decreaseTime();
        }
    }
}

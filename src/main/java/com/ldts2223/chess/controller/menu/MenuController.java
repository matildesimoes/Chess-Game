package com.ldts2223.chess.controller.menu;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.Settings;
import com.ldts2223.chess.model.menu.MainMenu;
import com.ldts2223.chess.model.menu.Menu;
import com.ldts2223.chess.states.MatchState;
import com.ldts2223.chess.states.MenuState;
import com.ldts2223.chess.states.SettingsState;
import static com.ldts2223.chess.gui.Input.ACTION.quit;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu menu) {
        super(menu);
    }

    public void step(Game game, Input input, long time){
        if (input.getAction() == quit) {
            game.setState(null);
            return;
        }

        int x = input.getPosition().getX();
        int y = input.getPosition().getY();
        int index = y - getModel().getEntryY();

        if (invalidInputPosition(x, index)) return;

        getModel().setSelectedEntry(index);

        String entry = getModel().getEntry(index);

        if (input.getAction() == Input.ACTION.mousebutton1)
            processClick(game, entry, time);
    }

    private boolean invalidInputPosition(int x, int index) {
        if (index < 0 || index >= getModel().getNumberEntries())
            return true;

        if(x < getModel().getEntryX() || x > getModel().getEntryX() + 10)
            return true;
        return false;
    }

    private void processClick(Game game, String entry, long time) {
        if (entry == "Start"){
            game.setState(new MatchState(new MatchBuilder().buildMatch(time)));
        }
        if (entry == "Settings"){
            game.setState(new SettingsState(Settings.getInstance()));
        }
        if (entry == "Quit"){
            game.setState(null);
        }
        if (entry == "Play Again")
            game.setState(new MatchState(new MatchBuilder().buildMatch(time)));

        if (entry == "Back To Menu")
            game.setState(new MenuState(new MainMenu()));
    }
}

package com.ldts2223.chess.controller.game;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.menu.GameOverMenu;
import com.ldts2223.chess.model.menu.MainMenu;
import com.ldts2223.chess.states.MenuState;

public class MatchController extends Controller<Match> {

    private final PlayerController playerController;

    public MatchController(Match match){
        super(match);
        playerController = new PlayerController(match);
    }

    public void step(Game game, Input input, long time) {
       if (input.getAction() == Input.ACTION.quit) {
            game.setState(new MenuState(new MainMenu()));
            return;
        }

        playerController.step(game, input, time);

        isCheckmate(game);
        isStalemate(game);
        isTimeOver(game);
    }

    private void isTimeOver(Game game) {
        if (getModel().isTimeOver()){
            if (getModel().getActivePlayer() == getModel().getWhitePlayer()) {
                game.setState(new MenuState(new GameOverMenu("Time Over! Black Won!")));
            }
            else game.setState(new MenuState(new GameOverMenu("Time Over! White Won!")));
        }
    }

    private void isStalemate(Game game) {
        if (getModel().isStaleMate())
            game.setState(new MenuState(new GameOverMenu("Stalemate! Draw!")));
    }

    private void isCheckmate(Game game) {
        if (getModel().isCheckMate()){
            if (getModel().getActivePlayer() == getModel().getWhitePlayer()) {
                game.setState(new MenuState(new GameOverMenu("Checkmate! Black Won!")));
            }
            else game.setState(new MenuState(new GameOverMenu("Checkmate! White Won!")));
        }
    }
}

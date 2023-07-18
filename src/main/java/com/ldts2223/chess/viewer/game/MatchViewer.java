package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.game.match.Board;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.viewer.Viewer;

import java.io.IOException;
import java.util.List;

public class MatchViewer extends Viewer<Match> {

    public MatchViewer(Match match) {
        super(match);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {

        for (Board board: getModel().getBoards().keySet()) {
            drawBoard(gui, new BoardViewer(board));

            List<Player> players = getModel().getBoards().get(board);
            drawPlayers(gui, players);
        }
    }
    private void drawPlayers(GUI gui, List<Player> players) throws IOException {
        for (Player player : players)
            drawPlayer(gui, new PlayerViewer(player));
    }
    private void drawPlayer(GUI gui, PlayerViewer viewer) throws IOException {
        viewer.draw(gui);
    }
    private void drawBoard(GUI gui, BoardViewer viewer) throws IOException {
        viewer.draw(gui);
    }
}

package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Board;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Play;
import com.ldts2223.chess.viewer.Viewer;

import java.util.HashSet;

public class MatchViewer extends Viewer<Match> {

    public MatchViewer(Match match) {
        super(match);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawBackground("#363636");

        drawBoard(gui);

        Player whitePLayer = getModel().getWhitePlayer();
        Player blackPlayer = getModel().getBlackPlayer();

        drawPlayers(gui, whitePLayer, blackPlayer);

        drawPieces(whitePLayer.getPieces(), gui);
        drawPieces(blackPlayer.getPieces(), gui);

        drawPlays(gui);

        drawTurn(gui);
    }

    private void drawPlays(GUI gui) {
        PlayViewer playViewer = new PlayViewer();
        for (Play play: getModel().getPlays())
            playViewer.draw(play, gui);
    }

    private static void drawPlayers(GUI gui, Player whitePLayer, Player blackPlayer) {
        PlayerViewer playerViewer = new PlayerViewer();

        playerViewer.draw(whitePLayer, gui);
        playerViewer.draw(blackPlayer, gui);
    }

    private void drawBoard(GUI gui) {
        Board board = getModel().getBoard();

        new BoardViewer().draw(board, gui);
    }

    private void drawTurn(GUI gui) {
        boolean isWhite = getModel().getActivePlayer().isWhite();
        gui.drawText(new Position(18,5), isWhite ? "White" : "Black",
                "#FFFFFF", "#363636" );
        gui.drawText(new Position(18,6), "Turn","#FFFFFF", "#363636" );
    }

    private void drawPieces(HashSet<Piece> pieces, GUI gui) {
        for (Piece piece: pieces) {
            gui.drawPiece(piece.getPosition(), piece.getImage(), piece.isWhite(), piece.isSelected());
        }
    }
}

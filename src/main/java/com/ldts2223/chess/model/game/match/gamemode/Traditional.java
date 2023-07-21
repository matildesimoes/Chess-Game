package com.ldts2223.chess.model.game.match.gamemode;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.*;
import com.ldts2223.chess.model.game.match.player.Player;

import java.util.HashSet;

public class Traditional implements GameMode {

    private int boardSize = 8;
    private String title = "Traditional";

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void load(Match match) {
        loadPlayer(match.getWhitePlayer());
        loadPlayer(match.getBlackPlayer());
    }

    private void loadPlayer(Player player) {
        HashSet<Piece> pieces = new HashSet<>();
        int y1;
        int y2;
        if (!player.isWhite()) {
            y1 = 1; y2 =2;
        }
        else {
            y1 = boardSize; y2 = boardSize - 1;
        }
        pieces.add(new Queen(new Position(4, y1), player.isWhite()));
        pieces.add(new Bishop(new Position(3, y1), player.isWhite()));
        pieces.add(new Bishop(new Position(6, y1), player.isWhite()));
        pieces.add(new Horse(new Position(2, y1), player.isWhite()));
        pieces.add(new Horse(new Position(7, y1), player.isWhite()));
        pieces.add(new Rook(new Position(1, y1), player.isWhite()));
        pieces.add(new Rook(new Position(8, y1), player.isWhite()));

        pieces.addAll(getPawns(player.isWhite(), y2, 1, boardSize));

        player.setKing(new King(new Position(5, y1), player.isWhite()));
        pieces.add(player.getKing());

        player.setPieces(pieces);
    }

    private HashSet<Pawn> getPawns(boolean isWhite, int y, int xBegin, int xEnd) {
        HashSet<Pawn> pawns = new HashSet<>();

        for (int i = xBegin; i <= xEnd; i++){
            pawns.add(new Pawn(new Position(i, y), isWhite));
        }
        return pawns;
    }
}

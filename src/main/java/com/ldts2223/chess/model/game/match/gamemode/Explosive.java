package com.ldts2223.chess.model.game.match.gamemode;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.*;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.ExplosivePawnRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.HorseWithQueenRules;
import java.util.HashSet;

public class Explosive implements GameMode {

    private int boardSize = 12;
    private String title = "Explosive";

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void load(Match match) {
        loadPlayer(match.getWhitePlayer());
        loadPlayer(match.getBlackPlayer());
    }

    private void loadPlayer(Player player){
        HashSet<Piece> pieces = new HashSet<>();
        int y = player.isWhite() ? boardSize : 1;
        int y3 = player.isWhite() ? boardSize - 2 : 3;
        int y2 = player.isWhite() ? boardSize - 1 : 2;

        pieces.add(new Rook(new Position(3, y), player.isWhite()));
        Horse horse1 = new Horse(new Position(4, y), player.isWhite());
        horse1.setRuleEngine(new HorseWithQueenRules());
        pieces.add(horse1);
        pieces.add(new Bishop(new Position(5, y), player.isWhite()));
        pieces.add(new Queen(new Position(6, y), player.isWhite()));
        King king = new King(new Position(7, y), player.isWhite());
        player.setKing(king);
        pieces.add(player.getKing());
        pieces.add(new Bishop(new Position(8, y), player.isWhite()));
        Horse horse2 = new Horse(new Position(9, y), player.isWhite());
        horse2.setRuleEngine(new HorseWithQueenRules());
        pieces.add(horse2);
        pieces.add(new Rook(new Position(10, y), player.isWhite()));

        pieces.addAll(getRooks(player.isWhite(), y2, 3, boardSize - 2));
        var pawns = getPawns(player.isWhite(), y3, 2, boardSize-1);
        pawns.add(new Pawn(new Position(2, y), player.isWhite()));
        pawns.add(new Pawn(new Position(2, y2), player.isWhite()));
        pawns.add(new Pawn(new Position(boardSize - 1, y), player.isWhite()));
        pawns.add(new Pawn(new Position(boardSize - 1, y2), player.isWhite()));

        for (Pawn pawn: pawns)
            pawn.setRuleEngine(new ExplosivePawnRules());
        pieces.addAll(pawns);

        player.setPieces(pieces);
    }

    private HashSet<Rook> getRooks(boolean isWhite, int y, int xBegin, int xEnd) {
        HashSet<Rook> rooks = new HashSet<>();

        for (int i = xBegin; i <= xEnd; i++){
            rooks.add(new Rook(new Position(i, y), isWhite));
        }
        return rooks;
    }

    private HashSet<Pawn> getPawns(boolean isWhite, int y, int xBegin, int xEnd) {

        HashSet<Pawn> pawns = new HashSet<>();

        for (int i = xBegin; i <= xEnd; i++){
            pawns.add(new Pawn(new Position(i, y), isWhite));
        }
        return pawns;
    }
}

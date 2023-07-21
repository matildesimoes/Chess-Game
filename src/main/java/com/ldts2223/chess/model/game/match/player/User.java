package com.ldts2223.chess.model.game.match.player;

import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Play;
import java.util.HashSet;

public class User extends Player{

    public User(long initialTime, boolean white) {
        super(initialTime, white);
    }

    public Player clone(){
        Player clone = new User(getTime(), isWhite());
        HashSet<Piece> pieces = new HashSet<>();
        for (Piece piece: getPieces()) {
            Piece pieceClone = piece.clone();
            if (piece instanceof King)
                clone.setKing((King) pieceClone);
            pieces.add(pieceClone);
        }
        clone.setPieces(pieces);
        return clone;
    }

    private void processClickedPiece(Position trueClick, Match match) {
        Piece clickedPiece = match.getPieceIn(trueClick);

        if (clickedPiece != null) {
            if (clickedPiece.isWhite() == match.getActivePlayer().isWhite()) {
                Piece previous = match.getSelectedPiece();
                if (previous != null)
                    previous.unselect();
                match.selectPiece(clickedPiece);;
                HashSet<Play> plays = match.filterCheckPlays(clickedPiece.getPossiblePlays(match));
                match.setPlays(plays);
            }
        }
    }

    private boolean processClickedPlay(long time, Play selectedPlay, Match match) {
        if (selectedPlay != null) {
            selectedPlay.execute(match);
            Piece previous = match.getSelectedPiece();
            if (previous != null) previous.unselect();

            match.switchTurn(time);
            match.selectPiece(null);
            return true;
        }
        return false;
    }

    public void play(long time, Match match, Input.ACTION action, Position click) {
        updateTime(time);

        if (action == Input.ACTION.mousebutton1) {

            Play selectedPlay = match.getPlayIn(click);

            boolean played = processClickedPlay(time, selectedPlay, match);

            if (!played)
                processClickedPiece(click, match);
        }
    }
}

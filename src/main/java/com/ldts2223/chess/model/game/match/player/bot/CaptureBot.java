package com.ldts2223.chess.model.game.match.player.bot;

import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.EnPassant;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class CaptureBot extends Bot {

    public CaptureBot(long initialTime, boolean white) {
        super(initialTime, white);
    }

    public Play selectPlay(Match match){
        HashSet<Play> plays;
        int randomNumber;
        plays = getPossiblePlays(match);
        plays = match.filterCheckPlays(plays);

        List<Play> playsList = new ArrayList<>(plays);
        match.setPlays(new HashSet<>());
        List<Play> priorityPlays = new ArrayList<>();
        for (Play play : plays) {
            if (isPriority(play))
                priorityPlays.add(play);
        }
        if (priorityPlays.size() == 0) {
            randomNumber = new Random().nextInt(playsList.size());
            return playsList.get(randomNumber);
        }
        randomNumber = new Random().nextInt(priorityPlays.size());
        return priorityPlays.get(randomNumber);
    }

    private boolean isPriority(Play play){
        return play instanceof Capture ||
                play instanceof EnPassant ||
                (play.getSelectedPiece() instanceof Pawn &&
                        play.getDestination().getY() == (play.getSelectedPiece().isWhite() ? 1 : 8));
    }

    public Player clone(){
        Player clone = new CaptureBot(getTime(), isWhite());
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
}

package com.ldts2223.chess.model.game.match.player.bot;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class RandomBot extends Bot {

    public RandomBot(long initialTime, boolean white) {
        super(initialTime,white);
    }

    public Play selectPlay(Match match){
        HashSet<Play> plays = getPossiblePlays(match);
        plays = match.filterCheckPlays(plays);
        List<Play> playsList = new ArrayList<>(plays);


        match.setPlays(new HashSet<>());
        int randomNumber = new Random().nextInt(playsList.size());
        return playsList.get(randomNumber);
    }

    public Player clone(){
        Player clone = new RandomBot(getTime(), isWhite());
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
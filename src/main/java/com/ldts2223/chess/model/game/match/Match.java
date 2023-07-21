package com.ldts2223.chess.model.game.match;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import java.util.*;

public class Match {

    private Set<Play> plays = new HashSet<>();
    private Piece pawnThatDoubled;
    private Piece selectedPiece;
    private Player whitePlayer;
    private Player blackPlayer;
    private boolean whiteTurn = true;
    private Board board = new Board(8);
    private long time;

    public Match(long time){
        this.time = time;
    }

    public void setPawnThatDoubled(Piece pawnThatDoubled) {
        this.pawnThatDoubled = pawnThatDoubled;
    }

    public Piece getPawnThatDoubled() {
        return pawnThatDoubled;
    }

    public void setBlackPlayer(Player blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public void setWhitePlayer(Player whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public Player getActivePlayer(){
        if (whiteTurn)
            return whitePlayer;
        return blackPlayer;
    }

    public Player getIdlePlayer(){
        if (whiteTurn)
            return blackPlayer;
        return whitePlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public void selectPiece(Piece piece) {
        if (piece != null)
            piece.select();
        selectedPiece = piece;
    }

    public Piece getSelectedPiece() {
        return selectedPiece;
    }

    public HashSet<Play> getPlays() {
        return (HashSet<Play>) plays;
    }

    public void setPlays(HashSet<Play> plays) {
        this.plays = plays;
    }

    public Play getPlayIn(Position position){
            for (Play play: plays){
                if (play.getDestination().equals(position))
                    return play;
            }
        return null;
    }

    public void emptyPlays(){
        plays = new HashSet<>();
    }

    public void switchTurn(long time){
        whiteTurn = !whiteTurn;
        getActivePlayer().setLastMove(time);
    }

    public Piece getPieceIn(Position position){
        for(Piece piece : whitePlayer.getPieces()){
            if(piece.getPosition().equals(position)) return piece;
        }
        for(Piece piece : blackPlayer.getPieces()){
            if(piece.getPosition().equals(position)) return piece;
        }
        return null;
    }

    public boolean check(){
        King king = getActivePlayer().getKing();
        return !avoidCheck(new Move(king.getPosition(), king));
    }

    public boolean isTimeOver(){
        if(getActivePlayer().getTime() <= 0){
            return true;
        }
        return false;
    }
    
    public HashSet<Play> filterCheckPlays(HashSet<Play> plays){
        HashSet<Play> filteredPlays = new HashSet<>();

        for (Play play: plays) {
            if (avoidCheck(play))
                filteredPlays.add(play);
        }
        return filteredPlays;
    }

    public boolean avoidCheck(Play play) {
        Match simulation = play.simulate(this);
        HashSet<Play> adversaryPlays = simulation.getActivePlayer().getPossiblePlays(simulation);
        boolean safe = true;

        for (Play advPlay: adversaryPlays){
            Match secondSimulation  = advPlay.simulate(simulation);
            if (!secondSimulation.isAlive(secondSimulation.getActivePlayer().getKing())) {
                safe = false;
                break;
            }
        }
        return safe;
    }

    public boolean isAlive(Piece piece){
        return getActivePlayer().getPieces().contains(piece) || getIdlePlayer().getPieces().contains(piece);
    }

    private boolean noPlaysAvailable(){
        return filterCheckPlays(getActivePlayer().getPossiblePlays(this)).size() == 0;
    }

    public boolean isCheckMate(){
        return (noPlaysAvailable() && check());
    }

    public boolean isStaleMate(){
        return (noPlaysAvailable() && !check());
    }

    @Override
    public Match clone(){
        Match match = new Match(time);
        match.setBoard(board.clone());
        match.setBlackPlayer(blackPlayer.clone());
        match.setWhitePlayer(whitePlayer.clone());
        if (this.getActivePlayer() == blackPlayer)
            match.switchTurn(time);
        match.selectPiece(getSelectedPiece());
        return match;
    }
}

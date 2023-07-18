package com.ldts2223.chess.model.game.match;

import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.rule.Rule;

import java.util.*;

public class Match {
    private Set<Rule> rules = new HashSet<>();
    private Map<Board, ArrayList<Player>> boards = new HashMap<>();

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public Map<Board, ArrayList<Player>> getBoards() {
        return boards;
    }

    public void setBoards(Map<Board, ArrayList<Player>> boards) {
        this.boards = boards;
    }

    public void addBoard(Board board, ArrayList<Player> players){
        this.boards.put(board, players);
    }
}

package com.ldts2223.chess.model;

import com.ldts2223.chess.model.game.match.Board;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.player.Bot;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MatchTest {

    private Match match;

    private Map<Board, ArrayList<Player>> boards;
    private Map<Board, ArrayList<Player>> expected;

    @BeforeEach
    void helper(){

        match = new Match();

        boards = new HashMap<>();
        boards.put(new Board(9,11), new ArrayList<Player>(Arrays.asList(new User(),new Bot())));

        match.setBoards(boards);

        expected = new HashMap<>();
        expected.put(new Board(9,11), new ArrayList<Player>(Arrays.asList(new User(),new Bot())));
        expected.put(new Board(6,8), new ArrayList<Player>(Arrays.asList(new Bot(),new Bot())));
    }

    @Test
    void addBoard() throws IOException {
        match.addBoard(new Board(6,8), new ArrayList<Player>(Arrays.asList(new Bot(),new Bot())));
        Assertions.assertEquals(expected, match.getBoards());
    }
}

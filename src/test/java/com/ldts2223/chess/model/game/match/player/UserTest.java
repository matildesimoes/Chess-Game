package com.ldts2223.chess.model.game.match.player;

import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Bishop;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;

public class UserTest {

    @Test
    public void testClone() {
        User user = new User(100, true);

        HashSet<Piece> pieces = new HashSet<>();
        pieces.add(new Bishop(new Position(2,3), true));
        pieces.add(new King(new Position(4,7), true));
        pieces.add(new Pawn(new Position(1,8), true));
        user.setPieces(pieces);

        Player clone = user.clone();

        Assertions.assertEquals(user, clone);
        Assertions.assertNotSame(user, clone);
    }

    @Test
    public void playNone() {
        User user = Mockito.spy(new User(100, true));
        Match match = Mockito.mock(Match.class);
        long time = 0;

        user.play(time, match, Input.ACTION.none, new Position(1,1));

        Mockito.verify(user).updateTime(time);
        Mockito.verify(match, Mockito.times(0)).getPlayIn(Mockito.any(Position.class));
        Mockito.verify(match, Mockito.times(0)).getSelectedPiece();
    }

    @Test
    public void selectPiece() {
        User user = Mockito.spy(new User(100, false));
        Match match = Mockito.mock(Match.class);
        Position clickPosition = new Position(1,1);
        Piece piece = Mockito.mock(Piece.class);
        Piece previous = Mockito.mock(Piece.class);
        long time = 0;

        Mockito.doReturn(user).when(match).getActivePlayer();
        Mockito.doReturn(previous).when(match).getSelectedPiece();
        Mockito.doReturn(null).when(match).getPlayIn(clickPosition);
        Mockito.doReturn(piece).when(match).getPieceIn(clickPosition);
        Mockito.doReturn(false).when(piece).getColor();

        user.play(time, match, Input.ACTION.mousebutton1, clickPosition);

        Mockito.verify(user).updateTime(time);
        Mockito.verify(match, Mockito.times(1)).getPlayIn(clickPosition);
        Mockito.verify(match, Mockito.times(1)).getSelectedPiece();
        Mockito.verify(match, Mockito.times(1)).getPieceIn(clickPosition);
        Mockito.verify(previous).unselect();
        Mockito.verify(match).selectPiece(piece);
        Mockito.verify(match).setPlays(Mockito.any());
    }

    @Test
    public void selectPlay() {
        User user = Mockito.spy(new User(100, true));
        Match match = Mockito.mock(Match.class);
        Position clickPosition = new Position(1,1);
        long time = 0;

        Play play = Mockito.mock(Move.class);
        Mockito.doReturn(play).when(match).getPlayIn(clickPosition);

        user.play(time, match, Input.ACTION.mousebutton1, clickPosition);

        Mockito.verify(user).updateTime(time);
        Mockito.verify(match, Mockito.times(1)).getPlayIn(clickPosition);
        Mockito.verify(match, Mockito.times(1)).getSelectedPiece();
        Mockito.verify(play).execute(match);
        Mockito.verify(match).switchTurn(time);
        Mockito.verify(match).selectPiece(null);
    }
}

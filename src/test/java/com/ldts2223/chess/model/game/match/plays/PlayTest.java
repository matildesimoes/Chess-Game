package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


public class PlayTest {

    private Position destination;
    private Pawn selectedPiece;
    private Play play;

    @BeforeEach
    void helper(){
        destination = new Position(3,4);
        selectedPiece = new Pawn(new Position(3,3),true);
        play = Mockito.spy(new Move(destination,selectedPiece));
    }

    @Test
    void testConstructor(){
        Assertions.assertEquals(destination, play.getDestination());
        Assertions.assertEquals(selectedPiece, play.getSelectedPiece());
    }

    @Test
    public void setAndGetColor(){
        play.setColor("#336699");
        Assertions.assertEquals("#336699", play.getColor());
    }

    @Test
    public void setAndGetImage(){
        play.setImage('-');
        Assertions.assertEquals('-', play.getImage());
    }

    @Test
    public void setAndGetDestination(){
        play.setDestination(new Position(3,3));
        Assertions.assertEquals(new Position(3,3), play.getDestination());
        Assertions.assertEquals(new Position(3,3), play.getPosition());
    }

    @Test
    public void setAndGetSelectedPiece(){
        Pawn pawn1 = new Pawn(new Position(3,3),true);
        play.setSelectedPiece(pawn1);

        Assertions.assertEquals(pawn1, play.getSelectedPiece());
    }

    @Test
    public void execute(){
        Match match = Mockito.mock(Match.class);
        play.execute(match);

        Mockito.verify(match).setPawnThatDoubled(null);
        Mockito.verify(play).furtherExecute(match);
        Mockito.verify(match).emptyPlays();
    }

    @Test
    public void simulate(){
        Match match = Mockito.mock(Match.class);
        Match simulation = Mockito.mock(Match.class);
        Mockito.doReturn(simulation).when(match).clone();
        Play simulatedPlay  = Mockito.mock(Play.class);
        Mockito.doReturn(simulatedPlay).when(play).clone(simulation);

        Assertions.assertEquals(simulation, play.simulate(match));

        Mockito.verify(match).clone();
        Mockito.verify(play).clone(simulation);
        Mockito.verify(simulatedPlay).execute(simulation);
        Mockito.verify(simulation).switchTurn(0);
    }
}

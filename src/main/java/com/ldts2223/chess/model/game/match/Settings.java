package com.ldts2223.chess.model.game.match;

import com.ldts2223.chess.model.game.match.gamemode.Explosive;
import com.ldts2223.chess.model.game.match.gamemode.GameMode;
import com.ldts2223.chess.model.game.match.gamemode.Traditional;

import java.util.Arrays;
import java.util.List;

public class Settings {

    private Integer time = 600;
    private List<String> whitePlayer = Arrays.asList("User", "Easy Bot", "Medium Bot");
    private int whitePlayerIndex = 0;
    private List<String> blackPlayer = Arrays.asList("User", "Easy Bot", "Medium Bot");
    private int blackPlayerIndex = 2;
    private List<GameMode> gamemode = Arrays.asList(new Traditional(), new Explosive());
    private int gamemodeIndex = 0;
    private int decreaseX = 9;
    private int increaseX = 22;
    private int entryY = 6;
    private String backEntry = "Back to Menu";
    private int backY = 13;
    private int backX = 4;
    private static Settings instance = null;

    private Settings(){}

    public static Settings getInstance(){
        if (Settings.instance == null)
            instance = new Settings();
        return Settings.instance;
    }

    public int getBackX() {
        return backX;
    }

    public int getBackY() {
        return backY;
    }

    public String getBackEntry() {
        return backEntry;
    }

    public int getEntryY() {
        return entryY;
    }

    public int getIncreaseX() {
        return increaseX;
    }

    public int getDecreaseX() {
        return decreaseX;
    }

    public String getWhitePlayer(){
        return whitePlayer.get(whitePlayerIndex);
    }

    public String getBlackPlayer(){
        return blackPlayer.get(blackPlayerIndex);
    }

    public GameMode getGamemode() {
        return gamemode.get(gamemodeIndex);
    }

    public long getTime() {
        return time;
    }

    public void reset(){
        whitePlayerIndex = 0;
        blackPlayerIndex = 2;
        time = 600;
        gamemodeIndex = 0;
    }

    public void incrementWhitePlayer(){
        whitePlayerIndex++;
        if (whitePlayerIndex >= whitePlayer.size())
            whitePlayerIndex = 0;
    }

    public void decrementWhitePlayer(){
        whitePlayerIndex--;
        if (whitePlayerIndex < 0)
            whitePlayerIndex = whitePlayer.size() - 1;
    }

    public void incrementBlackPlayer(){
        blackPlayerIndex++;
        if (blackPlayerIndex >= blackPlayer.size())
            blackPlayerIndex = 0;
    }

    public void decrementBlackPlayer(){
        blackPlayerIndex--;
        if (blackPlayerIndex < 0)
            blackPlayerIndex = blackPlayer.size() - 1;
    }

    public void incrementGamemode(){
        gamemodeIndex++;
        if (gamemodeIndex >= gamemode.size())
            gamemodeIndex = 0;
    }

    public void decrementGamemode(){
        gamemodeIndex--;
        if (gamemodeIndex < 0)
            gamemodeIndex = gamemode.size() - 1;
    }

    public void increaseTime(){
        if (time + 30 < (99 * 60 + 59))
            time += 30;
    }
    public void decreaseTime(){
        if (time > 30 )
            time -= 30;
    }
}

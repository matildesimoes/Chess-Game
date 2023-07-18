package com.ldts2223.chess.model.menu;

import java.util.Arrays;
import java.util.List;

public abstract class Menu {

    private List<String> entries;
    private int currentEntry = 0;

    public Menu() {
        this.setEntries(Arrays.asList("Start", "Change Game Settings", "Exit"));
    }

    public void setEntries(List<String> entries) {
        this.entries = entries;
    }

    public int getNumberEntries(){
        return entries.size();
    }

    public String getEntry(int i){
        return entries.get(i);
    }

    public Boolean isSelected(int i){
        return i == currentEntry;
    }
}

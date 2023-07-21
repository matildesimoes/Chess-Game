package com.ldts2223.chess.model.menu;

import java.util.List;

public abstract class Menu {

    private String title;
    private List<String> entries;
    private int selectedEntry = -1;
    private int entryX = 6;
    private int entryY = 6;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setEntryX(int entryX) {
        this.entryX = entryX;
    }

    public void setSelectedEntry(int selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    public Boolean isSelected(int i){
        return i == selectedEntry;
    }

    public int getEntryX() {
        return entryX;
    }

    public int getEntryY() {
        return entryY;
    }
}

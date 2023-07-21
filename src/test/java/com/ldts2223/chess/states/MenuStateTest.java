package com.ldts2223.chess.states;

import com.ldts2223.chess.model.menu.MainMenu;
import com.ldts2223.chess.model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuStateTest {

    private Menu menu;
    private MenuState menuState;

    @BeforeEach
    void setUp(){
        menu = new MainMenu();
        menuState = new MenuState(menu);
    }
    @Test
    void TestGetModel(){
        Assertions.assertEquals(menu,menuState.getModel());
    }
}

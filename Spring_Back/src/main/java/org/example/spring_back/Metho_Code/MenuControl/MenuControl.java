package org.example.spring_back.Metho_Code.MenuControl;

import org.example.spring_back.Menu.Menu;
import org.springframework.stereotype.Service;


@Service("MainMenuControl")
public class MenuControl {

    public Boolean Insert_Menu(Menu menuDTO) {

        if (menuDTO == null) {
            return false;
        }
        else return true;
    }
}
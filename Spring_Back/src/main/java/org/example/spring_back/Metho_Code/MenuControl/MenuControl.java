package org.example.spring_back.Metho_Code.MenuControl;

import org.example.spring_back.Menu.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenuControl {

    public Boolean Insert_Menu(Menu menuDTO) {

        if (menuDTO == null) {
            return false;
        }
        else return true;
    }
}
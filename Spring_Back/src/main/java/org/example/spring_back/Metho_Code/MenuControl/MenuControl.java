package org.example.spring_back.Metho_Code.MenuControl;

import org.example.spring_back.Menu.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class MenuControl {

    //메뉴 저장
    public String Insert_Menu(Menu menu){
        try {
            // 이미지 파일 저장
            MultipartFile file = menu.getMenuImage();
            if (file != null && !file.isEmpty()) {
                String destination = "파일 저장 경로" + file.getOriginalFilename();
                file.transferTo(new File(destination));
            }
            return "Success";
        } catch (IOException e) {
            return "Fail : " + e.getMessage();
        }
    }
}

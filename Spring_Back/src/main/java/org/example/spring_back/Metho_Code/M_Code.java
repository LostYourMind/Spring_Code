//M_Code.java


package org.example.spring_back.Metho_Code;

import org.example.spring_back.Menu.Menu;

import org.springframework.stereotype.Service;
import org.example.spring_back.User.User_Data;
import org.example.spring_back.User.LoginCredentials;


//Json 읽기
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class M_Code {

    //회원가입 기능
    public Boolean CreateUser(User_Data temp){
        String userName = temp.getUserName();
        String userID = temp.getUserId();
        String userPW = temp.getUserPw();
        String userEmail = temp.getUserEmail();

        User_Data u_date = new User_Data(userID, userPW, userName, userEmail);

        return u_date != null;
    }

    public Boolean AuthenticateUser(String username, String password){
        String jsonfile = "C:\\Users\\WSU\\Documents\\GitHub\\Spring\\Caps_Spring_Code\\Test\\src\\main\\java\\org\\example\\test\\User\\test.json";
        ObjectMapper mapper = new ObjectMapper();
        try {
            // JSON 파일에서 로그인 정보를 읽어옵니다.
            LoginCredentials credentials = mapper.readValue(new File(jsonfile), LoginCredentials.class);

            // 읽어온 정보와 입력 받은 정보를 비교합니다.
            if (credentials.getUsername().equals(username) && credentials.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


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

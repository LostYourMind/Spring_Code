package org.example.spring_back.Metho_Code;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.example.spring_back.User.User_Data;
import org.example.spring_back.User.LoginCredentials;
import org.springframework.stereotype.Service;
import org.example.spring_back.User.User_Data;

//Json 읽기
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;


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


    public Boolean Metho_NewMenu(Object newM){

        //들어오는 내용 Parsing 후 처리
        //DB에 저장 코드 생성(프로시저 가능하면 프로시저로)



        return true;
    }
}

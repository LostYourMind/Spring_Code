package org.example.spring_back;
import jakarta.servlet.http.HttpSession;
import org.example.spring_back.Metho_Code.M_Code;
import org.example.spring_back.User.User_Data;

import java.util.LinkedHashMap;

public class Control {

    M_Code code_ = new M_Code();

    //region 회원가입 & 로그인

    //회원가입 기능
    public String createUser(User_Data temp){
        boolean req = code_.CreateUser(temp);

        if(req){ return "성공"; }
        else {return "false";}
    }

    public Boolean AuthenticateUser(Object login_Req){

        LinkedHashMap<String, String> credentials = (LinkedHashMap<String, String>) login_Req;
        // LinkedHashMap에서 username과 password를 추출

        String user_id = credentials.get("loginId");
        String password = credentials.get("password");

        boolean req = code_.AuthenticateUser(user_id, password);
        if(req){ return true; }
        else {return false;}
    }


    //endregion


    //region 메뉴 관리

    //메뉴 등록
    public Boolean Insert_Menu(Menu menu){
        String temp = code_.Insert_Menu(menu);
        if(temp == "Success"){ return true; }
        else {return false;}
    }

    public Boolean New_Category(Object menu){

        return true;
    }

    //endregion

}

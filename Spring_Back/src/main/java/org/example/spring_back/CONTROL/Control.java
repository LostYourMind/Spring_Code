//Control.java

package org.example.spring_back.CONTROL;


import org.example.spring_back.DTOFILE.Menu.Menu;
import org.example.spring_back.Metho_Code.UserControl_CODE.M_Code;
import org.example.spring_back.Metho_Code.MenuControl_CODE.MenuControl;
import org.example.spring_back.DTOFILE.User.User_Data;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class Control {

    private final M_Code code_;
    private final MenuControl menuControl_;


    public Control(M_Code code_, MenuControl menuControl_) {
        this.code_ = code_;
        this.menuControl_ = menuControl_;
    }


    //region 회원가입 & 로그인

    //회원가입 기능
    public User_Data createUser(User_Data temp){
        User_Data req = code_.createUser(temp);

        return req;
    }

    //로그인 인증
    public Boolean AuthenticateUser(Object login_Req){

        LinkedHashMap<String, String> credentials = (LinkedHashMap<String, String>) login_Req;
        // LinkedHashMap에서 username과 password를 추출

        String user_id = credentials.get("loginId");
        String password = credentials.get("password");

        boolean req = code_.AuthenticateUser(user_id, password);
        if(req){ return true; }
        else {return false;}
    }

    //아이디 찾기 기능
    public String findUser(String useridValue){

        String temp = code_.findUser(useridValue);
        return temp;
    }

    public String findPW(String useridValue){
        String temp = code_.findUser_PW(useridValue);
        return temp;
    }


    //endregion


    //region 메뉴 관리

    //메뉴 등록
    public Boolean Con_Insert_Menu(Menu menuDataDTO){

        Boolean insert_result = menuControl_.insertMenu(menuDataDTO);
        return insert_result;
    }

    //메뉴 삭제
    public Boolean Con_Delete_Menu(String cName, String pName){
        boolean delete_result = menuControl_.deleteMenu(cName, pName);
        return delete_result;
    }

    public List<Object[]> menuGetList(String useridValue){
        return menuControl_.menuGetList(useridValue);
    }


    //endregion

    //region QR 코드 관리

//    public Boolean QR_SAVE(String kioskID, String QrCode){
//
//        QRCodeManage manage_ = new QRCodeManage();
//
//        QR_INFOMATION qr = manage_.saveQRCode(kioskID, QrCode);
//
//        if(qr == null){ return false; }
//        else return true;
//    }


    //endregion

}

//M_Code.java


package org.example.spring_back.Metho_Code;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.spring_back.Repository_Interface.UserRepository;

import org.example.spring_back.User.User_Data;
import org.example.spring_back.User.LoginCredentials;


//Json 읽기
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;



@Service
public class M_Code {

    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    //region 회원 관리 기능

    //회원가입 기능
    @Transactional
    public User_Data createUser(User_Data temp){
        String userName = temp.getUserName();
        String userID = temp.getUserId();
        String userPW = temp.getUserPw();
        String userEmail = temp.getUserEmail();

        entityManager.createStoredProcedureQuery("AddUser")
                .registerStoredProcedureParameter("userid", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("email", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("plain_password", String.class, ParameterMode.IN)
                .setParameter("userid", userID)
                .setParameter("username", userName)
                .setParameter("email", userEmail)
                .setParameter("plain_password", userPW)
                .execute();
        return userRepository.save(temp);  // 데이터베이스에 사용자 정보 저장
    }

    //로그인 기능
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


    public String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null; // 쿠키가 없는 경우 null 반환
    }

    //endregion


}

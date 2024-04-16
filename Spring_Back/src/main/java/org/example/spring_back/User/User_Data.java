package org.example.spring_back.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User_Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String user_ID;
    private String user_PW;
    private String user_Name;
    private String user_Email;


    public String getUserId() {
        return user_ID;
    }
    public void setUserId(String userId) {
        this.user_ID = userId;
    }

    public String getUserPw() {
        return user_PW;
    }
    public void setUserPw(String userPw) {
        this.user_PW = userPw;
    }

    public String getUserName() {
        return user_Name;
    }
    public void setUserName(String userName) {
        this.user_Name = userName;
    }

    public String getUserEmail() {
        return user_Email;
    }
    public void setUserEmail(String userEmail) {
        this.user_Email = userEmail;
    }

    public User_Data() {

    }

    // User 클래스의 생성자, 필요한 로직 추가 가능
    public User_Data(String userId, String userPw, String userName, String userEmail) {
        this.user_ID = userId;
        this.user_PW = userPw;
        this.user_Name = userName;
        this.user_Email = userEmail;
    }


}

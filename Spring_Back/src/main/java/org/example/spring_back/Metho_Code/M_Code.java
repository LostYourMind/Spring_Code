//M_Code.java

package org.example.spring_back.Metho_Code;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.example.spring_back.Repository_Interface.UserRepository;

import org.example.spring_back.User.User_Data;


@Service
public class M_Code {

    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger(M_Code.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public M_Code(UserRepository userRepository, EntityManager entityManager){
        this.userRepository = userRepository;
        this.entityManager = entityManager;
    }

    //region 회원 관리 기능

    // 회원가입 기능
    @Transactional
    public User_Data createUser(User_Data user) {

        logger.trace("Start createUser");

        String userId = user.getUserId();
        String password = user.getUserPw();
        String name = user.getUserName();
        String email = user.getUserEmail();


        logger.info("{} {} {} {}", userId, password, name, email);

        userRepository.insertUser(userId, password, name, email);

        logger.info("Finish createUser");
        return user;
    }

    //로그인 기능
    public Boolean AuthenticateUser(String userid, String password){

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("LoginUser");
        query.registerStoredProcedureParameter("userid", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("pass_W", String.class, ParameterMode.IN);
        query.setParameter("userid", userid);
        query.setParameter("pass_W", password);
        query.execute();

        Boolean result = (Boolean) query.getSingleResult();  // 프로시저의 반환 값을 Boolean으로 받음
        return result != null && result;
    }

    
    //사람 찾아버리는 기능
    public String findUser(String UserEmail){
        //찾아버려!!!기능 넣어줘
        return "5678";
    }

    //endregion
}

package org.example.spring_back;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring_back.Menu.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.spring_back.User.User_Data;
import org.springframework.web.multipart.MultipartFile;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackApplication.class, args);
	}

}



//관리자 로그인 기능 및 회원가입 기능
@RestController
class AdminController {

	private final Logger logger = LogManager.getLogger(AdminController.class);
	Control control_ = new Control();

	//회원가입 EndPoint
	@PostMapping("/join")
	public ResponseEntity<Boolean> createUser(@RequestBody User_Data user) {
		String result = control_.createUser(user);
		try{
			if("성공".equals(result)) {
				logger.info("createUser operation was success");
			}
			return ResponseEntity.ok(true); // HTTP 200 with true
		}
		catch(Exception e) {
			logger.error("An error occurred during the createUser operation", e);
			return ResponseEntity.badRequest().body(false); // HTTP 400 with false
		}
	}

	//로그인 EndPoint
	@PostMapping("/login")
	public ResponseEntity<String> Login(@RequestBody Object login_Req, HttpServletRequest request, HttpServletResponse response) {

		LinkedHashMap<String, String> credentials = (LinkedHashMap<String, String>) login_Req;

		String user_id = credentials.get("loginId");
		HttpSession session;
		boolean result = control_.AuthenticateUser(login_Req);
		try{
			if (result) {
				session = request.getSession(true); // 현재 세션을 반환하거나 없으면 새 세션 생성
				if (session.isNew()) {
					session.setAttribute("username", user_id); // 세션에 사용자 이름 저장
				}
				logger.info("login operation was success : {}",session.getId());
			}
			return ResponseEntity.ok("Login successful");
		}catch(Exception e) {
			logger.error("An error occurred during the login operation", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
		}
	}

	//로그아웃 EndPoint
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {

		logger.trace("Logout operation was Start");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // 세션 종료
		}

		Cookie loginCookie = new Cookie("user_id", null); // 로그아웃 시 쿠키 삭제
		loginCookie.setMaxAge(0); // 즉시 만료
		loginCookie.setPath("/");
		response.addCookie(loginCookie);
		logger.info("logout operation was success");
		return ResponseEntity.ok("Logged out successfully");
	}

	//아이디 찾기 EndPoint
	@PutMapping("/findid")
	public ResponseEntity<?> findUser(@RequestBody User_Data user) {

		String emailValue = user.getUserEmail();

		try{
			String result = control_.findUser(emailValue);
			if(result != null) {
				logger.info("findUser operation was success");
				return ResponseEntity.ok("User PW : " + result);
			}
			else {
				logger.info("findUser operation was failed");
				throw new Exception();
			}
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body(null);
		}


	}

	//비밀번호 찾기 EndPoint
	@PutMapping("/findPw")
	public ResponseEntity<String> findPw(@RequestBody User_Data user) {
		return ResponseEntity.ok("user");
	}
}



//관리자 메뉴 관련 기능
@RestController
class MenuControl {

	private final Logger logger = LogManager.getLogger(MenuControl.class);
	Control control_ = new Control();

	//메뉴 저장버튼
	@PostMapping("/insert-menu")
	public ResponseEntity<?> insertMenu(@RequestBody Menu menuDataDTO) {

		try{
			Boolean insert_Result = control_.Insert_Menu(menuDataDTO);
			if(insert_Result == false){
				throw new Exception();
			}
			logger.info("insertMenu operation was success");
			return ResponseEntity.ok(menuDataDTO);
		}
		catch(Exception e) {return ResponseEntity.badRequest().body("Insert Menu failed : " + e.getMessage());}
	}



	//메뉴 삭제
	@DeleteMapping("/del-menu")
	public ResponseEntity<String> Delete_Menu(@RequestBody List<Menu> menu) {

		return ResponseEntity.ok(menu.toString());
	}

}

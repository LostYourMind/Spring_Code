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


import java.util.LinkedHashMap;
import java.util.List;

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

				// 새로 추가된 부분: 로그인 성공 쿠키 설정
				Cookie loginCookie = new Cookie("user_login", user_id);
				loginCookie.setMaxAge(30 * 60); // 30분 동안 유효
				loginCookie.setHttpOnly(true); // JavaScript 쿠키에 접근하지 못하도록 설정
				loginCookie.setPath("/"); // 모든 경로에서 쿠키 접근 가능
				loginCookie.setSecure(true);

				// SameSite 설정을 위한 쿠키 문자열 직접 추가
				String cookieHeader = String.format("%s=%s; Path=%s; Max-Age=%s; Secure; HttpOnly; SameSite=None",
						loginCookie.getName(), loginCookie.getValue(), loginCookie.getPath(), loginCookie.getMaxAge());
				response.setHeader("Set-Cookie", cookieHeader);

				logger.info("login operation was success : {}, {}",session.getId(),loginCookie.getName());
			}
			return ResponseEntity.ok("Login successful");
		}catch(Exception e) {
			logger.error("An error occurred during the login operation", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
		}
	}

	@PostMapping("/info")
	public String getUserInfo(HttpServletRequest request) {
		Cookie[] list = request.getCookies();
		logger.info("Cookies: {}", list.length);
		for(Cookie cookie:list) {
			if(cookie.getName().equals("user_login")) {
				logger.info(cookie.getValue());
			}
		}
		return "test";


		/*logger.trace("Starting performSomeLogic method");
		Cookie[] cookies = request.getCookies();
		logger.info("HttpServletRequest = {}",request);
		logger.info("cookies = {}", cookies);
		if (cookies == null) {
			logger.error("No Cookies found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No cookies found");
		}

		String sessionId = null;
		for (Cookie cookie : cookies) {
			if ("JSESSIONID".equals(cookie.getName())) {
				sessionId = cookie.getValue();
				logger.info("JSESSIONID = {}", sessionId);
				break;
			}
		}

		if (sessionId == null) {
			logger.error("No JSESSIONID found");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JSESSIONID not found");
		}

		return ResponseEntity.ok("Session ID: " + sessionId + "Cookies: " + cookies);*/
	}


	@PutMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false); // 현재 세션 가져오기, 없으면 null 반환

		try{
			if (session != null) {
				session.invalidate(); // 세션 파기
				logger.info("logout operation was successful");
			}
			return ResponseEntity.ok("Logout successful");
		}catch(Exception e) {
			logger.error("An error occurred during the logout", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Logout failed");
		}
	}
}



//관리자 메뉴 관련 기능
@RestController
class MenuControl {

	private final Logger logger = LogManager.getLogger(MenuControl.class);
	Control control_ = new Control();

	//메뉴 저장버튼
	@PostMapping("/insert-menu")
	public ResponseEntity<String> Insert_Menu(@RequestBody List<Menu> menu) {

		logger.trace("Insert_Menu Operation Start");
		logger.info("Insert_Menu Operation End");
		return ResponseEntity.ok(menu.toString());

		/*if(control_.Insert_Menu((Menu) menu)) {
			return ResponseEntity.ok(menu.toString());
		}
		else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Insert Menu failed");*/

	}

	//메뉴 삭제
	@DeleteMapping("/del-menu")
	public ResponseEntity<String> Delete_Menu(@RequestBody List<Menu> menu) {

		return ResponseEntity.ok(menu.toString());
	}
	
	
	//카테고리 생성
	@PostMapping("/new_category_menu")
	public ResponseEntity<String> New_Category(@RequestBody Object category) {

		if(control_.New_Category(category)){
			return ResponseEntity.ok("Save Success!");
		}
		else return ResponseEntity.badRequest().body("Fail to Insert Category...");
	}

	//카테고리 삭제
	@DeleteMapping("/del-category")
	public ResponseEntity<String> Delete_Category(@RequestBody Object category) {
		return ResponseEntity.ok(category.toString());
	}

}

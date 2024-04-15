package org.example.spring_back;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.example.spring_back.User.User_Data;
import java.util.LinkedHashMap;

@SpringBootApplication
public class SpringBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBackApplication.class, args);
	}

}


//관리자 로그인 기능 및 회원가입 기능

@RestController
class AdminController {

	Control control_ = new Control();

	//회원가입 EndPoint
	@PostMapping("/join")

	public ResponseEntity<Boolean> createUser(@RequestBody User_Data user) {
		String result = control_.createUser(user);
		if("성공".equals(result)) {
			return ResponseEntity.ok(true); // HTTP 200 with true
		} else {
			return ResponseEntity.badRequest().body(false); // HTTP 400 with false
		}
	}

	//로그인 EndPoint
	@PostMapping("/login")
	public ResponseEntity Login(@RequestBody Object login_Req, HttpSession session) {

		LinkedHashMap<String, String> credentials = (LinkedHashMap<String, String>) login_Req;

		String user_id = credentials.get("loginId");

		boolean result = control_.AuthenticateUser(login_Req);
		if (result) {
			session.setAttribute("USER_ID", user_id);
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.badRequest().body(false);
		}
	}

	@PutMapping("/logout")
	public String  logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false); // 현재 세션 가져오기, 없으면 null 반환

		if (session != null) {
			session.invalidate(); // 세션 파기
		}
		return "redirect:/login"; // 로그인 페이지나 홈으로 리다이렉트
	}
}


//관리자 메뉴 관련 기능
@RestController
class MenuControl {

	Control control_ = new Control();

	//메뉴 저장버튼
	@PostMapping("/insert-menu")
	public ResponseEntity<String> Insert_Menu(@RequestBody List<Menu> menu) {

		return ResponseEntity.ok(menu.toString());
		/*if(control_.Insert_Menu((Menu) menu)){
			return ResponseEntity.ok("Save Success!");
		}
		else return ResponseEntity.badRequest().body("Fail to Insert Menu...");*/
	}

	@PostMapping("/new_category_menu")
	public ResponseEntity<String> New_Category(@RequestBody Object category) {

		if(control_.New_Category(category)){
			return ResponseEntity.ok("Save Success!");
		}
		else return ResponseEntity.badRequest().body("Fail to Insert Category...");
	}
}

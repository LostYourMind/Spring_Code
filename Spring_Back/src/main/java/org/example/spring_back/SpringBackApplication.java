package org.example.spring_back;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.spring_back.Menu.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<String> Login(@RequestBody Object login_Req, HttpServletRequest request) {
		LinkedHashMap<String, String> credentials = (LinkedHashMap<String, String>) login_Req;

		String user_id = credentials.get("loginId");

		boolean result = control_.AuthenticateUser(login_Req);

		if (result) {
			HttpSession session = request.getSession(true); // 현재 세션을 반환하거나 없으면 새 세션 생성
			if (session.isNew()) {
				// 필요한 경우, 세션에 추가 데이터를 저장합니다.
				session.setAttribute("username", user_id);
			}
			return ResponseEntity.ok("Login successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
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


		if(control_.Insert_Menu((Menu) menu)){


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




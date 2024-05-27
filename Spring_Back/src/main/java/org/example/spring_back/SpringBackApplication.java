package org.example.spring_back;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.spring_back.CONTROL.Control;
import org.example.spring_back.DTOFILE.Menu.Menu;
import org.example.spring_back.DTOFILE.User.InfoRe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.spring_back.DTOFILE.User.User_Data;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import java.io.File;
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

	//region 맴버필트 & 생성자

	private final Logger logger = LogManager.getLogger(AdminController.class);

	private final Control control_;

	@Autowired
	public AdminController(Control control) {
		this.control_ = control;
	}

	//endregion


	//region 회원가입 & 로그인 & 아이디 + 비밀번호 찾기

	//회원가입 EndPoint
	@PostMapping("/admin/join")
	public ResponseEntity<Boolean> createUser(@RequestBody User_Data user) {

		User_Data result = control_.createUser(user);
		try{
			if(result == null){ throw new Exception(); }
            logger.info("User created : {}", user);
			return ResponseEntity.ok(true);
		}
		catch(Exception e) {
			logger.error("An error occurred during the createUser operation", e);
			return ResponseEntity.badRequest().body(false); // HTTP 400 with false
		}
	}


	//로그인 EndPoint
	@PostMapping("/admin/login")
	public ResponseEntity<String> Login(@RequestBody Object login_Req, HttpServletRequest request) {

		LinkedHashMap<String, String> credentials = (LinkedHashMap<String, String>) login_Req;

		String user_id = credentials.get("loginId");
		boolean result = control_.AuthenticateUser(login_Req);
		try {
			if (result) {
				HttpSession session = request.getSession(true);  // 새로운 세션을 생성하거나 기존 세션을 가져옵니다.
				session.setAttribute("username", user_id);       // 항상 사용자 ID를 세션에 저장합니다.
				logger.info("Login operation was successful: {}", session.getId());
				return ResponseEntity.ok("Login successful");
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
			}
		} catch (Exception e) {
			logger.error("An error occurred during the login operation", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
		}
	}

	@GetMapping("/info")
	public ResponseEntity<?> test(HttpServletRequest request) {
		InfoRe test = new InfoRe();

		test.setLoginId("2222");
		test.setUserName("이종혁");

		return ResponseEntity.ok(test);
	}


	//로그아웃 EndPoint
	@PostMapping("/admin/logout")
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
	@PutMapping("/admin/findid")
	public ResponseEntity<?> findUser(@RequestBody User_Data user) {
		//이메일 기준으로 아이디 찾기
		
		String EmailValue = user.getUserEmail();

		try{
			String result = control_.findUser(EmailValue);
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
			logger.error("An error occurred during the findUser operation : {}", e);
			return ResponseEntity.badRequest().body(null);
		}


	}

	//비밀번호 찾기 EndPoint
	@PutMapping("/admin/findPw")
	public ResponseEntity<String> findPw(@RequestBody User_Data user) {
		
		
		String value = user.getUserPw();
		try{
			String result = control_.findPW(value);
			if(result != null) {
				logger.info("findPw operation was success");
				return ResponseEntity.ok("PW : " + result);
			}
			else { logger.info("findPw operation was failed"); throw new Exception(); }
		}
		catch(Exception e) {
			logger.error("An error occurred during the findPw operation : {}", e);
			return ResponseEntity.badRequest().body(null);
		}

	}

	//endregion

}


//관리자 메뉴 관련 기능
@RestController
class MenuControl {

	//region 맴버필드 & 생성자

	private final Logger logger = LogManager.getLogger(MenuControl.class);

	private final Control control_;

	@Autowired
	public MenuControl(Control control) {
		this.control_ = control;
	}

	//endregion


	//region 메뉴 관리 기능

	//메뉴 저장버튼
	@PostMapping("/admin/insert-menu")
	public ResponseEntity<?> insertMenu(@RequestBody Menu menuDataDTO) {

		logger.info("insertMenu Start {} ", menuDataDTO);

		try{
			Boolean insert_Result = control_.Con_Insert_Menu(menuDataDTO);
			if(!insert_Result){
				throw new Exception();
			}
			logger.info("insertMenu operation was success");
			return ResponseEntity.ok(menuDataDTO);
		}
		catch(Exception e) {return ResponseEntity.badRequest().body("Insert Menu failed : " + e.getMessage());}
	}


	//메뉴 삭제
	@DeleteMapping("/admin/del-menu")
	public ResponseEntity deleteMenu(@RequestBody String cName, String pName) {

		return (ResponseEntity) ResponseEntity.ok();
	}


	//메뉴 전체 출력
	@PostMapping("/admin/menulist")
	public ResponseEntity<?> menuListAll(@RequestBody User_Data userID) {

		logger.info("User {} Request : [menuListALL] is Start ", userID);
		String temp = userID.getUserId();

		try{
			List<Object[]> test = control_.menuGetList(temp);
			if(test == null){
				throw new Exception();
			}
			logger.info("User {} Request [menuListAll] is Finish", userID);
			return ResponseEntity.ok(test);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(null);
		}

	}

	//endregion

}


@RestController
class UserPage{

	private final Logger logger = LogManager.getLogger(UserPage.class);

	private final Control control_;

	@Autowired
    UserPage(Control control) {
        control_ = control;
    }

    //region 사용자 페이지

	@PostMapping("/user/kioskpage")
	public ResponseEntity<?> menuListAll(@RequestBody User_Data userID) {

		logger.info("User {} Request : [/user/kioskpage] is Start ", userID);
		String temp = userID.getUserId();
		try{
			List<Object[]> test = control_.menuGetList(temp);
			if(test == null){
				throw new Exception();
			}
			logger.info("User {} Request [menuListAll] is Finish", userID);
			return ResponseEntity.ok(test);
		}catch (Exception e){
			return ResponseEntity.badRequest().body(null);
		}

	}

	//endregion


	//region QR 저장 코드



	//endregion


}


@RestController
class ImageController {

	/**
	 * 이 메서드는 이미지 파일에 대한 GET 요청을 처리.
	 * 지정된 디렉토리에서 이미지 파일을 읽고 이를 응답으로 반환.
	 *
	 * @param filename 클라이언트가 요청한 이미지 파일의 이름
	 * @return 이미지 파일을 리소스로 포함한 ResponseEntity 또는 오류 상태
	 */
	@GetMapping("/images/{filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String filename) {
		try {
			// 이미지 파일이 저장된 디렉토리 경로 설정
			String directoryPath = "C:\\Users\\WSU\\Documents\\GitHub\\CapStone_Spring_Code\\Spring_Code\\Spring_Back\\src\\main\\java\\org\\example\\spring_back\\TEST_Image_File";

			// 요청된 이미지 파일에 대한 File 객체 생성
			File file = new File(directoryPath, filename);

			// 파일이 존재하는지 확인
			if (!file.exists()) {
				// 파일이 존재하지 않으면 404 Not Found 상태 반환
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			// 파일을 FileSystemResource로 생성
			Resource resource = new FileSystemResource(file);

			// 파일을 포함한 응답 엔티티를 200 OK 상태로 반환
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
					.body(resource);
		} catch (Exception e) {
			// 오류가 발생하면 500 Internal Server Error 상태 반환
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

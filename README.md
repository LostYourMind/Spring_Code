1.프로젝트 클론 하시고요
2.해당 프로젝트 파일 중 pom.xml 파일 있을꺼거등요??
3.해당 파일 우클릭해서 아래 Maven 들어가셔서 로드 해주시고요
4.SpringBackApplication 들어가서 실행하시면요
5.실행된답니다.



🎁Ps. 아직 DB 연동 안해서 DB는 사용 못해요...최대한 빨리 DB 열어서 작업할 수 있게 해드릴께
🎁PPs. DB 테이블이랑은 다 만들었는데, 테스트 해봐야해서 좀 걸려유

✔ 현재 구현된 내용 ✔

✔ 회원가입
✔ 로그인
✔ 로그아웃


✨ 현재 구현중인 내용 ✨

 메뉴 등록 ( Json 데이터 DB 저장 코드 개발 중 )



😷 구현해야 할 내용 😷
 메뉴 삭제
 
파일 구조
![image](https://github.com/LostYourMind/Spring_Code/assets/42758008/fb42f83d-5634-4c59-a87b-4fae807106c5)



👓 파일 설명 👓

SpringBackApplication -> 메인 어플리케이션 (AdminController -> 회원 관리 기능) (MenuControl -> 메뉴 관리 기능)

Control -> 컨트롤 파일 (기능 연결)

Metho_Code(DIR) => M_Code -> 회원 관리 기능 관련 처리 코드(모델 코드)

Metho_Code(DIR) => MenuControl(DIR) => MenuControl -> 메뉴 관리 기능 관련 처리 코드(모델 코드)

config(DIR) => WebConfig -> CORS관련 처리

resources(DIR) => application.properties- -> 서버 설정(포트 설정 & DB 연동 설정)



🗃 DB 연동 방법 🗃

spring.datasource.url=jdbc:mysql://localhost:포트/DB이름?useSSL=false
spring.datasource.username=아이디
spring.datasource.password=비밀번호
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


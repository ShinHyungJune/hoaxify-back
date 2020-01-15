package com.example.hoaxify.user;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 랜덤포트로 테스트 실행
@ActiveProfiles("test") // real, dev, test인지
class UserControllerTest {

    @Autowired // testRestTemplate을 생성자에서 주입할 필요 없이 @Autowired를 넣어주면 자동주입됨.
    TestRestTemplate testRestTemplate;
    /* TestRestTemplate이 뭐지?
    - api 통신 테스트를 도움
    * */

    @Autowired
    UserRepository userRepository;

    @BeforeEach // 테스트 시작하기전에 아래 메소드를 실행
    void setup()
    {
        userRepository.deleteAll(); // db 비우기
    }

    private User createUser()
    {
        User user = new User();

        user.setName("test");

        user.setPassword("password");

        return user;
    }

    @Test // 테스트 케이스 위에는 @Test를 붙여줘야 이걸 테스트함
    public void a_guest_can_sign_up()
    {
        User user = createUser();

        // testRestTemplate.postForEntity(URL, 보낼 데이터, 반환받을 데이터 타입);
        // [rootURL]/api/1.0/users에 user 데이터를 담아 post 통신을 보냄
        ResponseEntity<Object> response = testRestTemplate.postForEntity("/api/1.0/users", user, Object.class);

        long userCount = userRepository.count();

        assertEquals(userCount,1);
    }
}
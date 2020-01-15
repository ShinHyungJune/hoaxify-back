package com.example.hoaxify.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service // 서비스 클래스에는 이 어노테이션을 넣어줘야함
public class UserService {

    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder;

    // Service에서 Repository를 주입할 때는
    // @autowired를 통한 자동주입이 아닌 생성자로 직접 주입해야
    // 나중에 유닛테스트할 때 편하다고 함.
    public UserService(UserRepository userRepository){
        super();
        this.userRepository = userRepository;

        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // password 해쉬암호화

        return userRepository.save(user);
    }
}

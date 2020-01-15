package com.example.hoaxify.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA를 사용해서 테이블과 매핑할 클래스는 @Entity를 붙여 JPA에게 이 클래스 관리해주세요~ 라고 말해줘야함
@Table(name = "users") // 테이블명
@Data
// @Data는 setter, getter, 생성자, 해쉬코드 등을 자동생성
// 그런데 @Data를 쓰면 나중에 연관관계 설정 시 @ToString(exclude = 연관관계변수명)을 설정해줘야 무한루프를 안돈다고함
// 또한 setter가 기본으로 들어가있기때문에 객체의 안정성을 보장할 수 없다는데 고수가 아니므로 패스
public class User {

    @Id // 이 컬럼이 primary key라는걸 알림
    @GeneratedValue // primary key를 데이터 저장 때마다 자동생성해주세요~
    private Long id;

    private String name;

    private String password;
}

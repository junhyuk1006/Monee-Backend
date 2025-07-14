package com.example.moneebackend.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @Builder 와 @AllArgsConstructor 을 함께 사용
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String nickname;

    // @Enumerated(EnumType.STRING)을 붙여야 DB에 "ACTIVE" 같은 문자열로 저장
    // 기본은 ORDINAL(?)
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private LocalDateTime createdAt;

    // User 객체가 저장되기 전에 자동 호출
    // @PrePersist save() 하기 직전
    // @PostPersist save() 직후
    // @PreUpdate 수정되기 전
    // @PostUpdate 수정 직후
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void deactivate(){
        this.status = UserStatus.DELETED;
    }
}

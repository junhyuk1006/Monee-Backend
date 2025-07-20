package com.example.moneebackend.dto.user;

import lombok.Getter;
@Getter
public class UserSignupRequestDto {
    private String email;
    private String password;
    private String nickname;
}

/*
왜 DTO를 사용하는가?
1. Entity 직접 노출 방지
- User 엔티티에 비밀번호, 내부 상태 등 노출하면 안 되는 민감한 정보가 있을 수 있음
- 클라이언트가 User 엔티티를 그대로 받아보거나 요청 보내는 건 위험함

2. 요청/응답 구조 분리
- 요청 DTO: 클라이언트가 서버에 전달하는 구조
- 예: UserSignupRequestDto

- 응답 DTO: 서버가 클라이언트에게 주는 데이터 구조
- 예: UserResponseDto

3. 유연한 구조 변경
- 예를 들어 nickname 필드를 나중에 제거하거나 추가해도
Entity가 아니라 DTO만 바꾸면 되므로 유지보수가 쉬움

4. Validation(검증) 을 쉽게 걸 수 있음
- DTO에 @NotNull, @Email, @Size 등 검증 조건 붙이기 편함
*/

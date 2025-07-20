# Monee - Backend

가계부 웹앱 Monee의 Spring Boot 백엔드입니다.  
사용자 인증 및 트랜잭션 데이터 관리, JWT 인증 기반 API를 제공합니다.

---

## 🛠 사용 기술

- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- JPA (Hibernate)
- MySQL
- Gradle

---

## 📁 프로젝트 구조

src/
├── config/ # CORS, Security 설정 등
├── controller/ # API 컨트롤러
├── dto/ # 요청/응답 DTO
├── entity/ # JPA 엔티티
├── repository/ # JPA 리포지토리
├── service/ # 비즈니스 로직

---

## 🔐 인증 방식

- JWT (Access Token 방식)
- 로그인 시 토큰 응답 → 클라이언트에서 저장
- 요청 시 `Authorization: Bearer {token}` 헤더 전송

---

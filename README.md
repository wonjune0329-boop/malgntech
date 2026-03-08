# CMS Content Management API

Spring Boot 기반으로 구현한 간단한 **콘텐츠 관리(CMS) REST API** 프로젝트입니다.
콘텐츠 CRUD 기능과 JWT 기반 로그인 인증 및 사용자 권한 처리를 구현했습니다.

---

# 개발 환경 (Spec)

* Java 25
* Spring Boot 4
* Spring Security
* Spring Data JPA
* H2 Database
* Lombok
* Swagger (API 테스트)

---

# 프로젝트 실행 방법

### 1. 프로젝트 실행

프로젝트 루트에서 실행

```
./gradlew bootRun
```

또는 IntelliJ에서

```
MalgnApplication 실행
```

---

### 2. 서버 주소

```
http://localhost:8080
```

---

### 3. Swagger API 문서

```
http://localhost:8080/swagger-ui/index.html
```

Swagger UI를 통해 모든 API를 테스트할 수 있습니다.

---

### 4. H2 Database

H2 Console 접속

```
http://localhost:8080/h2-console
```

JDBC URL

```
jdbc:h2:mem:testdb
```

---

# 데이터 모델

### Contents

| 컬럼명                | 설명     | 데이터 타입                |
| ------------------ | ------ | --------------------- |
| id                 | 고유 아이디 | bigint primary key    |
| title              | 콘텐츠 제목 | varchar(100) not null |
| description        | 콘텐츠 내용 | text                  |
| view_count         | 조회수    | bigint not null       |
| created_date       | 생성일    | timestamp             |
| created_by         | 생성자    | varchar(50) not null  |
| last_modified_date | 수정일    | timestamp             |
| last_modified_by   | 수정자    | varchar(50)           |

---

# 구현 기능

## 콘텐츠 CRUD

* 콘텐츠 생성
* 콘텐츠 목록 조회 (페이징 처리)
* 콘텐츠 상세 조회
* 콘텐츠 수정
* 콘텐츠 삭제

콘텐츠 상세 조회 시 **조회수(view_count)가 증가하도록 구현했습니다.**

---

## 로그인 기능

Spring Security 기반 로그인 기능 구현

```
POST /auth/login
```

Request

```json
{
  "username": "user1",
  "password": "1234"
}
```

로그인 성공 시 **JWT Token을 발급합니다.**

---

## 접근 권한

Role 기반 접근 제어 구현

| 역할    | 권한                      |
| ----- | ----------------------- |
| USER  | 본인이 작성한 콘텐츠만 수정 / 삭제 가능 |
| ADMIN | 모든 콘텐츠 수정 / 삭제 가능       |

---

# API 목록

| Method | URL            | 설명              |
| ------ | -------------- | --------------- |
| POST   | /auth/login    | 로그인             |
| POST   | /contents      | 콘텐츠 생성          |
| GET    | /contents      | 콘텐츠 목록 조회 (페이징) |
| GET    | /contents/{id} | 콘텐츠 상세 조회       |
| PUT    | /contents/{id} | 콘텐츠 수정          |
| DELETE | /contents/{id} | 콘텐츠 삭제          |

---

# 추가 구현 기능

* JWT(Json Web Token) 기반 인증 처리
* Spring Security Filter 기반 인증 처리
* Swagger API 문서 및 테스트 환경 구성
* DTO 패턴 적용 (Request / Response 분리)
* 페이징 처리 구현
* 조회수 증가 로직 구현
* 작성자 권한 검증 로직 구현

---

# 프로젝트 구조

```
src
 └ main
     ├ controller
     ├ service
     ├ repository
     ├ entity
     ├ dto
     ├ security
     └ config
```

---

# 사용한 AI 도구 및 참고 자료

### AI 도구

* ChatGPT (OpenAI)

사용 목적

* Spring Security 및 JWT 구조 검토
* 코드 구조 점검
* README 문서 작성 보조

---

### 참고 자료

* Spring Boot 공식 문서
* Spring Security 공식 문서
* Baeldung (Spring Security / JWT 관련 자료)

---
본 프로젝트는 제공된 요구사항을 기반으로 Spring Boot 프로젝트를 새로 생성하여 구현했습니다.
# 작성자

윤원준

# ⭐️[일정 관리 앱 서버 구현 프로젝트]⭐️

## ⚙️ 개발 환경 및 라이브러리
### 📌 개발 환경
✔️ 언어: Java 17</br>
✔️ 빌드 도구: Gradle</br>
✔️ 프레임워크: Spring Boot 3.4.1</br>
✔️ 데이터베이스: MySQL</br>
### 📌 사용 라이브러리
✔️ `spring-boot-starter-web`<br>
✔️ `spring-boot-starter-data-jdbc`<br>
✔️ `spring-boot-starter-validation`<br>
✔️ `lombok`<br>

## 👉🏻 ERD
![erd.png](readme_images/erd.png)

## 👉🏻 API 명세서
![img_1.png](readme_images/api.png)

## 👉🏻구현 사항
[x] 일정 생성</br>
[x] 일정 조회(전체 일정, 수정일, 작성자명, id 기준)</br>
[x] 선택 일정 삭제</br>
[x] 선택 일정 수정</br>
[x] 작성자 테이블 설계 및 일정 테이블에 외래키 설정</br>
[x] Page 객체를 활용한 페이지별 일정 목록 조회(페이지 번호, 페이지 크기 기준)</br>
[x] @ExceptionHandler를 활용한 Custom Exception 생성 예외처리</br>
[x] @Valid, @Pattern을 통해 유효성 검증</br>


## 👉🏻 POSTMAN 확인
<details>
  <summary><b>작성자 생성</b></summary>

![create_author.png](readme_images/create_author.png)

</details>

<details>
  <summary><b>작성자 수정</b></summary>
  
![update_author.png](update_author.png)

</details>

<details>
  <summary><b>일정 생성</b></summary>

  ![img_3.png](readme_images/create_schedule.png)

</details>

<details>
  <summary><b>일정 수정</b></summary>
  
![img_3.png](readme_images/img_3.png)

</details>

<details>
  <summary><b>일정 삭제</b></summary>
  
![img_4.png](readme_images/img_4.png)
</details>

<details>
  <summary><b>선택 일정 조회</b></summary>

  ![img_13.png](readme_images/img_13.png)
</details>
<details>
  <summary><b>전체 일정 조회</b></summary>
  
- 조건 없이 조회하는 경우
![img_7.png](readme_images/img_7.png)
- updatedDate 으로만 조회하는 경우
![img_5.png](readme_images/img_5.png)
- 모든 조건으로 조회하는 경우
![img_6.png](readme_images/img_6.png)
</details>

<details>
  <summary><b>페이지별 일정 조회</b></summary>
  
![img_8.png](readme_images/img_8.png)
</details>

<details>
  <summary><b>예외 발생 시 response</b></summary>

- 이메일 형식이 올바르지 않은 경우
![img_12.png](readme_images/img_12.png)
- body에 필수 입력값을 입력하지 않은 경우
![img_11.png](readme_images/img_11.png)
- 올바르지 않은 id 경로를 입력한 경우
![img_9.png](readme_images/img_9.png)
- 비밀번호가 올바르지 않은 경우
![img_10.png](readme_images/img_10.png)
</details>
<br>


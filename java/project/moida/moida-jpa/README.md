## 로컬 개발환경 세팅

---
#### mysql 계정 생성 및 db 추가
```
GRANT ALL PRIVILEGES ON *.* TO 'moida'@'localhost' IDENTIFIED BY 'password' WITH GRANT OPTION;

CREATE DATABASE moida DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```

---
#### 로컬 서버 시작시 config location 설정
* IntelliJ -> Run -> Edit Configurations...
* Application 추가 또는 선택 후 Environments -> VM Options에 다음 설정 추가

```
-Dspring.config.location=classpath:/config/
```

## Bigquery와 MySQL 연동하기

```
// download embulk.bat
PowerShell -Command "& {Invoke-WebRequest http://dl.embulk.org/embulk-latest.jar -OutFile embulk.bat}"

// install plugins
embulk gem install embulk-output-command
embulk gem install embulk-input-mysql
embulk gem install embulk-output-bigquery
embulk gem list

// make config.yml & run
embulk run config.yml

// run 명령어를 토대로 embulk가 작업을 진행
1. local에 csv 생성(AppData\Local\Temp\) 
2. gcs_bucket에 Insert
3. bigquery Job에 load 작업을 등록하여 temp table에 로드
4. gcs_bucket에서 csv 을 삭제
5. temp table에서 config.yml에 설정한 project/dataset/table으로 데이터를 옮김
6. temp table을 삭제
7. local의 csv 파일을 삭제
```

```
// example
embulk example
- 예제 데이터를 생성

embulk guess embulk-example/seed.yml
- seed.yml파일을 기반으로 설정을 추론합니다

embulk guess embulk-example/seed.yml -o config.yml
- seed.yml파일을 기반으로 설정파일인 config.yml을 생성합니다

embulk preview config.yml
- config.yml을 읽어 데이터를 파싱이 진행되는지 테스트합니다

embulk run config.yml
- config.yml을 읽어 실행합니다
```

```json
config.yml > 
exec:
  max_threads: 16
  min_output_tasks: 4

in:
    type: mysql
    use_legacy_datetime_code: true
    host: localhost
    port: 3306
    user: root   
    password: [PASSWORD]   
    database: [DBNAME] 
    query: |
        SELECT code, sido, sigungu, dong
        FROM address
        order_by code DESC
```
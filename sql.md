# SQL
___
data type

char <-> varchar  고정 문자열, 가변문자열

char <-> nchar char(10) -> 영문자10개, 한글5개 저장한는 10byte 생성
, nchar(10) -> 문자10개, 한글 10개 저장한는 20byte 생성


## DDL - 데이터 정의어(Data Definition Language)
>CREATE, ALTER, DROP

### CREATE TABLE 
테이블 생성 명령어
```roomsql
CREATE TABLE member(
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(20) DEFAULT '0'              
)
NOT NULL, CONSTRAINT 조건 추가가능                                  
```


### ALTER TABLE 
컬럼 추가, 수정(DATA TYPE, DATA SIZE), 삭제 명령어

- 컬럼 추가
```roomsql
ALTER TABLE products
ADD pdate datetime null -> 초기값주며 컬럼추가
```
- 컬럼 수정
```roomsql
ALTER TABLE products
ALTER COLUMN price VARCHAR(10) -> 데이터가 있다면 DATA TYPE 수정 불가,
크기는 같거나 크게 변경가능
```
- 컬럼 삭제
```roomsql
ALTER TABLE products
DROP COLUMN price 
```

### DROP TABLE
테이블 삭제 명령어
```roomsql
DROP TABLE products
```

### TRUNCATE TABLE
테이블의 모든 로우 삭제 명령어(스키마는 존재)
```roomsql
TRUNCATE TABLE products
```
___
* NULL - 비교 연산불가, 빈공간 아님, 존재하는 값

## DML - 데이터 조작어(Data Manipulation Language)
>SELECT, UPDATE, DELETE, INSERT
```roomsql
SELECT DISTINCT ename '사원이름' -> 중복을 제거하는 DISTINCT,
                                   출력 컬럼명 ALIAS
FROM department
```




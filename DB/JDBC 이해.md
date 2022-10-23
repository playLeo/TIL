클라이언트 요청 -> 애플리케이션 서버 -> DB

옛날 애플리케이션와 DB간의 사용법
1. 커넥션 연결(TCP/IP)
2. SQL전달
3. 결과 응답

문제는 각각의 데이터베이스마다 커넥션 연결 방법, SQL 질의 방법, 응답방법이 달랐다

이러한 문제로 DB 변경시 애플리케이션 서버에 개발된 데이터베이스 로직도 변경해야했고,

DB에 맞는 언어도 새로 학습해야 했다.

이러한 문제를 해결하기위해 JDBC 가 등장

JDBC가 인터페이스로 정의해 제공한다. -> 인터페이스를 구현해서 DB에 접근하는 것을 MySQL Driver, Oracle Driver 라고 부른다.

jdbc를 편하게 사용할 수 있는 기술은 크게 2가지
1. SQL Mapper - JDBC Template, Mybatis - SQL 응답 결과를 객체로 편리하게 반환한다 + JDBC의 반복 코드르 제거한다 BUT 개발자가 SQL을 직접 작성해야한다.
2. ORM - 객체와 DB를 매핑해준다.

Connection connection = DriverManager.getConnection(url, username, passward);

라이브러리에 DB가 들어가 있다면 라이브러리에서 DriverManager가 조회해서 connection을 연결 할  수있다.


```java
package hello.jdbc.repository;
import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import java.sql.*;
/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values(?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        //null은 왜 깔고 가는거지?
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            //여기 까지 바인딩 작업, 쿼리를 날리지 않은 상태
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }
    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
    }
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }}
```

요청마다 db 드라이버를 통해 새로운 커넥션을 얻으면 커넥션마다 db와 tcp/IP 통신을 하고 데이터를 받는 과정이 오래걸린다.
미리 커넥션 풀에 tcp/IP 통신을 하고 있는 커넥션들을 저장해놓고 요청이 오면 커넥션을 건내주는 방식을 취한다.
보통 커넥션풀의 커넥션은 10개정도를 유지한다.

커넥션 풀에서 커넥션을 사용후 반납하면 연결을 종료하는 것이 아니라 살아 있는 상태로 반환 해야한다. -> close하면 알아서 풀에 반납

커넥션풀의 이점이 상당해서 실무에서는 기본으로 사용한다. 이점은 무한대로 커넥션이 연결되는걸 방지해주는 이점이 있다.
hikariCP이 춘추 전국시대에서 승리해서 이 커넥션풀을 사용하면 된다.
boot를 사용하면 기본적으로 적용되어 있다.

JDBC 쓰다가 hikariCP
DBCP2 쓰다가 hikarCP 이런식으로
커넥션풀을 안쓰다가 쓰는경우, 쓰다가 변경하는경우 커넥션을 얻는 코드가 다르기 때문에 수정이 필요하다
이를 해결하기 위해 DataSource 인터페이스로 추상화 해놨다.

DriverManaver는 커넥션풀을 사용하지않고 요청시 커넥션을 받는 형식이다.

## Datasource를 사용해보자.

DriverManager 만 사용해서 connection을 가져오면 
DriverManager.getConnection(url, username, password); 커넥션을 가져올 때 마다 db 정보를 같이 넘겨줘야 했다.

datasource를 사용해서 커넥션을 얻으면
DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);에서 db 정보를 넘겨주고
사용시 dataSource.getConnection()을 사용해 설정과 사용을 분리해 많은 장점을 가져와준다.


위 까지는 datasource 추상화를 사용했지만 connection 을 직접 얻어오는 drivermanager를 사용했고  이번엔 
이번엔 커넥션풀을 사용한느 datasource를 사용해 보자.

커넥션과 세션 -> 커넥션마다 세션이 실행되고 커넥션이 종료 될 때 까지 세션은 살아있다.

세션은 트랜잭션을 시작하고 커밋, 롤백등을 수행하고 종료한다. 종료 후 다른 트랙잭션을 시작할 수 있다.

## 트랜잭션 ACID
원자성 - 하나의 트랙잭션은 모두 실행 되거나 모두 실행되지 않거나

일관성 - 모든 트랜잭션은 일관성있는 데이터베이스 상태를 유지해야한다.(무결성 조건)

격리성 - 동시에 실행되는 트랜잭션은 서로에게 영향을 미치면 안된다.

지속성 - 트랜잭션이 성공적으로 끝나면 항상 기록되어야 한다. 중간에 문제가 발생해도 로그를 이용해 복구할 수 있어야 한다.

우리가 사용하고 있는 쿼리는 설정은 오토 커밋을 사용하고 있기 때문에 쿼리를 날리면 바로 DB에 반영이된다. 

설정에서 오토커밋을 수동커밋으로 돌리고 db를 확인해보면 커밋하기 전 데이터를 데이터를 추가한 세션에서는 확인이 가능하지만 다른 세션에서는 
확인할 수 없다.

만약 커밋전 임시로 올라간 데이터가 다른 세션에도 저장되어있다면 다른 세션에서 조회했을때 임시로 저장한 값인지 정식 커밋된 값인지 알 방법이 없다.

자동 커밋이 되어있다면 트랜잭션을 사용할 수 없다.
수동커밋으로 돌려주고 트랜잭션 기능을 사용해야 한다. -> 트랜잭션을 시작한다고 볼 수 있다.

set autocommit false 
이후 쿼리를 날리고 커밋 or 롤백 명령어를 항상 넣어줘야 한다. 안하면 타임아웃이 설정되어 있어 자동 롤백됨.

DB에서 직접 쿼리를 날리면서 오토커밋 수동커밋하는건 쉬웠다. 콘솔 열면 콘솔 하나가 하나의 세션을 갖는듯 하다.
개발자의 실수로 트랜잭션의 일부만 db에 임시 저장되었다면 롤백을 통해 원래의 상태로 되돌아가면되는데 일부만 성공했음을 어떻게 알까 ?

-> exception 발생시 rollback 지정해서 사용하는 듯

## DB락

세션1 이 커밋을 하지 않은 상태에서 데이터를 넣었는데 세션2도 같은 데이터를 넣었다면 원자성이 깨지는 문제와 기타등등의 문제가 발생한다.

세션이 트랜잭션을 시작하고 데이터를 수정하는 동안에는 커밋이나 롤백 전까지 다른 세션에서 해당 데이터를 수정할 수 없게 막아야한다.

세션은 트랜잭션을 수행하기전에 해당 데이터에 대한 락을 획득하고 트랜잭션을 수행한다.


____

## 중간정리

초기 JDBC를 이용해 데이터 베이스를 조작하려면 
connection을 DriverManager를 통해 TCP/IP 통신을 하며 그때 끄때 연결해서 사용했다.

이러한 방식은 커넥션의 수를 조절할 수 없고, TCP/IP 통신을 하며 시간도 오래걸렸다. 그래서 커넥션을 미리 만들어두고
쓰고 반납하는 형태의 커넥션 풀이 등장했다.

인터페이스인 DataSource를 사용해서 커넥션을 얻는다.

Repo에서는 DataSource를 DI 받아 사용하는 형식으로 사용한다. 물론 스프링이 관리해주는 빈처럼 만들어져 있지 않기 때문에
Repo를 사용할때 DataSource를 만들고 파라미터로 넘겨준다.

```java
//Test를 하기위해 Repo를 만들고, Repo를 만들때 DataSource를 만들고 매개변수로 넣어준다.
HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPoolName(PASSWORD);

        memberRepository = new MemberRepositoryV1(dataSource);
```
위의 코드는 일일이 커넥션을 연결하는 JDBC의 getConnection이 아닌 Hikari를 사용해 커넥션풀을 사용하고 DataSource인터페이스를
상속받은 HikariDataSource를 이용했다.

실제 코드레벨에서 트랜잭션을 구현하기 위해서는 repo에서 커넥션을 반환하면 안되고 비지니스 로직을 실현하는 service에서 트랜잭션을
수행하고 종료하는 작업을 수행 해야한다.

service에서도 Datasource를 주입받고 사용하게 설계하고 Datasource.get을 통해 한 커넥션을 가져와 트랜잭션을 실행할 메서드에 
매개변수로 넣어줘 같은 커넥션과 세션을 사용하게 만들어 트랜잭션을 수행 할 수 있다.
모든 비지니스 로직을 수행후 con.rollback(), con,commit() 등의 마무리 작업을 수행하고 
비지니스 로직전 con.setAutuCommit(false)를 해주고 커밋 롤백후에도 오토커밋모드로 변경해놔야 안전한다.

비지니스 로직에 이러한 트랜잭션 코드와 매개변수로 커넥션을 넘겨주는 것은 코드가 지저분해지는 것과 커넥션을 받는 메서드 
안받는 메서드 등등을 따로 만들려면 상당히 피곤한 작업을 진행해야한다. 

문제점들
1. 트랜잭션을 적용하기 위해 JDBC 구현 기술이 서비스 계층에 누수되었다.
2. 서비스 계층은 순수해야한다. -> 구현기술을 JDBC -> JPA로 변경해도 변경이 없어야한다.
3. 트랜잭션 동기화 문제 -> 파라미터로 connection을 넘겨서 사용했다.
4. 트랜잭션 반복 문제 -> try catch finally ....
5. 예외 누수, JDBC 반복 문제 ...

우리는 스프링이 제공해주는 트랙잭션을 사용해서 이러한 문제들을 해결해 보려한다.

# 스프링에서의 트랜잭션

트랜잭션을 추상화해 트랜잭션 추상화 인터페이스를 만든다.

스프링에는 PlatformTansactionManager로 추상화 되어있고 -> 트랜잭션 매니저

OOO TransactionManager로 각각 구현 기술마다 구현체도 만들어져 있다.

JDBC에 의존적인 문제들도 있었다. SQLException, con = dataSource.getConnection(), con.setAutoCommit(false) 등등 JPA에서는 트랜잭션 시작은 transaction.begin()이다 

PlatformTansactionManager의 메서드
1. getTransaction() : 트랜잭션을 시작한다. get으로 네이밍된 이유는 이미 진행중인 트랜잭션이 있는경우 참여 할 수 있기 때문이다.
2. commin() : 트랜잭션을 커밋한다.
3. rollback() : 트랜잭션을 롤백한다.

스프링이 제공하는 트랜잭션 매니저는 크게 2가지 역할을 한다.
1. 트랜잭션 추상화
2. 리소스 동기화 - 트랜잭션을 유지하려면 트랜잭션의 시작부터 끝까지 같은 데이터베이스 커넥션을 유지해야한다. 이전
방법에서는 파라미터로 넘겨서 사용했지만, '쓰레드 로컬'을 사용해서 커넥션을 동기화해준다.

동작방식을 간단하게 설명하면 다음과 같다.
1. 서비스 계층에서 transactionManager.getTransaction()을 호출해서 트랜잭션을 시작한다.
2. 트랜잭션을 시작하려면 커넥션이 필요하다. 트랜잭션 매니저는 데이터소스를 통해 커넥션을 만들고 트랜잭션을 실행한다.
3. 커넥션을 수동 커밋 모드로 변경해서 실제 데이터베이스 트랜잭션을 시작한다
4. 커넥션을 트랜잭션 동기화 매니저에 보관한다.
5. 매니저는쓰레드로컬에 커넥션을 보관한다. 따라사 멀티 쓰레드 환경에 안전하게 커넥션을 보관할 수 있다.
6. 서비스 비지니스 로직을 실행하면 리포지토리의 메서드들을 호출한다. 이때 커넥션을 파라미터로 전달하지 않는다.
7. 리포지토리 메서드들은 트랜잭션이 시작된 커넥션이 필요하다. DataSourceUtils.getConnection(dataSouce)
8. 획득한 커넥션을 사용해서 SQL을 실행한다.
9. 비지니스 로직이 끝나고 트랜잭션을 종료한다. 트랜잭션은 커밋이나 롤백하면 종료된다.
10. 트랜잭션을 종료하려면 동기화된 커넥션이 필요하다. 트랜잭션 동기화 매니저를 통해 동기화된 커넥션을 획득한다.
11. 획득한 커넥션 통해 데이터베이스에 트랜잭션을 커밋or 롤백
12. 전체 리소스 정리(쓰레드 로컬 정리, autocommit모드로 변경, con.colse() 호출)


### 선언적 트랜잭션 관리 vs 프로그래밍 방식 트랜잭션 관리

@Transaction - 선언적 트랜잭션 관리
트랜잭션 매니저 or 트랜잭션 템플릿 등을 사용해서 트랜잭션 관련 코드를 직접 작성하는 것



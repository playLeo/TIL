## URI(Uniform Resource Identifier)

* 인터넷에 있는 접근 가능한 자원을 나타내는 유일한 주소를 일관되게 표현하는 형식. 통합 자원 식별자 즉, 인터넷 자원을 식별할 수 있는 문자열을 의미한다.

![uri img](https://velog.velcdn.com/images%2Fyounoah%2Fpost%2Fb476852f-ffbf-44ed-b407-0449c69c65d3%2Fimage.png)

## URL(Uniform Resource Locator)

* 네트워크 상에서 리소스가 위치한 정보를 나타낸다.
* HTTP 프로토콜 뿐만 아니라 FTP, SMTP등 다른 프로토콜에서도 사용 가능하다.

구조

![URL structure](https://hanseul-lee.github.io/2020/12/24/20-12-24-URL/uri.png)

1. Protocol(Scheme)
* 어떤 방식으로 자원에 접근할 것인가 하는 약속 규칙
* http, https, ftp 등등
* https 는 http에 보안 추가(HTTP Secure)

2. Host
* 도메인명 or IP주소

3. Port
* 접속 포트. 일반적으로 생략. http : 80, https : 443

4. Path
* 리소스 경로. 계층적 구조

5. Query
* key=value 형태
* query parameter, query string 등으로 불린다.

6. Fragment
* html 내부 북마크 등에 사용한다.
* 서버로 전송되지 않는다.


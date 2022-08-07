## HTTP 헤더
HTTP 전송에 필요한 모든 부가정보

RFC723x
* 엔티티 -> 표현(Representation)
* REpresentation = representation Metadata + Representation Data


### 표현
* Content-Type: 표현 데이터의 형식
  * ex) text/html; charset=utf-8, application/json, image/png
* Content-Encoding: 표현 데이터의 압축 방식. 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제
  * ex) gzip, deflate, identity
* Content-Language: 표현 데이터의 자연 언어
  * ex) ko, en, en-US
* Content-Length: 표현 데이터의 길이(바이트 단위)
  * Transfer-Encoding(전송코딩)을 사용하면 Content-Length를 사용하면 안된다.


### 협상(Content Negotiation)
클라이언트가 선호하는 표현 요청

 * Accept: 클라이언트가 선호하는 미디어 타입 전달
 * Accept-Charset: 클라이언트가 선호하는 문자 인코딩
 * Accept-Encoding: 클라이언트가 선호하는 압축 인코딩
 * Accept-Language: 클라이언트가 선호하는 자연 언어

협상과 우선순위
* Quality Valuese(q) 값 사용
* 0~1, 클수록 높은 우선순위
* 생략하면 1
* ex) Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
___

### 일반정보
* From - 유저 에이전트의 이메일 정보
* Referer - 이전 웹 페이지 주소(유입 경로 분석 가능)
* User-Agent - 유저 에이전트 애플리케이션 정보(브라우저 통계가능)
* Server - 요청을 처리한는 ORIGIN 서버 소프트웨어 정보. 응답에서만 사용
* DATE - 메세지가 발생한 날짜와 시간. 응답에서만 사용

### 특별한 정보
* Host - 요청한 호스트 정보(도메인)
  * 필수
  * 하나의 서버가 여러 도메인을 처리해야 할 때
  * 하나의 IP 주소에 여러 도메인이 적용되어 있을 때
* Location - 페이지 리다이렉션
* Allow - 허용 가능한 HTTP 메서드
* Retry-After - 유저 에이전트가 다음 요청을 하기까지 기다려야 한는 시간

### 쿠키
* Set-Cookie: 서버가 클라이언트로 쿠키 전달(응답)
* Cookie: 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달
* 사용자 로그인 세션 관리
* 광고 정보 트래킹
* 쿠키 정보는 항상 서버에 전송되기 때문에 네트워크 트래픽을 추가로 유발하고 최소한의 정보만 사용해야한다(세션 id, 인증토큰)
* 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶으면 웹스토리지

쿠키를 한번 저장하면, 사용자가 모든요청에 쿠키정보를 자동으로 포함한다.
![cookie img](https://velog.velcdn.com/images%2Fgil0127%2Fpost%2Fc1f08dd5-1baa-470b-a54a-ab0888ca38f6%2F222222.PNG)

#### 쿠키 생명주기
* Set-CookieL expires=Sta, 26-Dec-2020 04:30:21 GMT
  * 만료일이 되면 쿠키 삭제
* Set-Cookie: max-age=3600(3600초)
  * 0 or 음수를 지정시 쿠키삭제
* 세션 쿠키: 만료 날짜를 생략하면 브라우저 종료시 까지만 유지
* 영속 쿠키: 만료 날짜를 입력하면 해당 날짜까지 유지  

#### 쿠키 도메인
* 쿠키를 보낼 도메인 명시: 명시한 문서 기준 도메인 + 서브 도메인 
ex) domain=example.org를 지정했다면, example.org + dev.example.org같은 서브도메인도 쿠키 접근

* 쿠키를 보낼 도메인 생략: 현재 문서 기준 도메인만 적용
키를 생성한 example.org 에서만 쿠키 접근 가능

#### 쿠키 경로
* 이 경로를 포함한 하위 경로 페이지만 쿠키 접근
* 일반적으로 path=/ 루트로 지정한다.


#### 쿠키 보안

* Secure
  * 쿠키는 http, https를 구분하지 않고 전송
  * Secure를 적용하면 https인 경우에만 전송
* HttpOnly
  * XSS 공격 방지
  * 자바스크립트에서 접근불가(doucument.cookie)
* SameSite
  * XSRF 공격 방지
  * 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송

## 캐시와 조건부 요청



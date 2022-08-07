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


![cache img](https://velog.velcdn.com/images%2Fleemember%2Fpost%2F2e619ff2-e755-4205-a4ef-7ad44134e276%2F%EC%A0%9C%EB%AA%A9%20%EC%97%86%EC%9D%8C-1.jpg)

* 브라우저 캐시를 사용해 동일한 결과가 응답되는 상황에서 네트워크를 사용하지 않고 빠른 로딩 속도로 캐시에서 데이터를 가져온다.(캐시 가능 시간동안)
* 캐시 시간 초과 후, 응답의 결과가 캐시에 있는 값과 동일할 경우, 똑같은 데이터를 다시 받아야 하는 문제가 발생한다.

**검증 헤더 추가** 를 통해 조건부 요청(시간을 간단하게 표기했지면 utc 표준으로 사용해야 한다.)

### Last-Modified

캐시에 값이 없을때 검증 헤더를 포함한 데이터를 받는다.

이후 캐시값의 마지막 수정 시간을 기준으로 데이터의 변경 여부를 판별한다.

![cacheader](https://velog.velcdn.com/images%2Fleemember%2Fpost%2F6c1a3864-0fa7-4672-aaf3-53c9287e0fa4%2Fimage.png)
![img](https://velog.velcdn.com/images%2Fleemember%2Fpost%2Fcd037efc-1e81-4b5e-9799-8d27189ead2e%2Fimage.png)

정리
* 캐시 유효 시간이 초과해도, 서버의 데이터가 갱신되지 않으면 바디 없이 304 Not Modified 헤더 메타 정보만 응답한다.
* 클라이언트는 서버가 보낸 응답 헤더 정보로 캐시의 메타 정보를 갱신한다.
* 결과적으로 네트워크 다운로드가 발생하지만 용량이 적은 헤더정보만 다운로드 한다.

단점
* 1초 미만 단위로 캐시 조정이 불가능하다.
* 날짜 기반의 로직을 사용해야한다.
* 데이터를 수정해서 날짜가 다르지만, 결과가 똑같은 경우, 갱신은 없지만 다운로드 해야한다.
* 서버에서 별도의 캐시 로직을 관리하고 싶은 경우(주석변경은 캐시 유지 등)

### ETag(Entitiy Tag)
캐시용 데이터에 임의의 고유한 버전 이름을 달아둔다.


## 캐시와 조건부 요청 헤더
* Cache-Control: max-age
 * 캐시 유호 시간, 초 단위
* Cache-Control: no-cache
 * 데이터는 캐시해도 되지만, 항상 원(origin)서버에 검증하고 사용
* Cache-Control: no-store
 * 데이터에 민감한 정보가 있으므로 저장하면 안된다.

* Pragma(캐시 제어)
* Expires(캐시 만료일 지정)

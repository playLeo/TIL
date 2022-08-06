## 상태코드
클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능

* 1xx(Informational): 요청이 수신되어 처리중
* 2xx(Successful): 요청 정상 처리
  * 200 OK - ex) GET으로 요청이 성공되었을 때
  * 201 Created - ex) POST로 새로운 리소스 생성이 되었을 때
  * 202 Accepted - 요청이 접수되었으나 처리가 왼료되지 않았다 - 배치 처리 같은 곳에서 사용
  * 204 No Content - 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없다 - ex) 네이버 메모 save 작동
    * 상태코드만 내려주고 화면은 유지해야 하기 때문에 메세지 내용이 없다.   
* 3xx(Redirection): 요청을 완료하려면 추가 행동이 필요
  * 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동(리다이렉트)
  * 영구 리다이렉션
    * 301 Moved Permanently - 리다이렉트 요청 메서드가 GET으로 변하고, 본문이 제거 될 수 있다.(주로 리다이렉트를 하면 데이터도 변하기 때문에 301을 많이 사용한다.)
    * 308 Permanent Redirect - 301과 기능은 같지만, 리다이렉트시 요청 메서드와 본문 유지
  * 일시적 리다이렉션
    * 리소스의 URI가 일시적으로 변경되지만, URL을 변경하면 안된다. 
    * 302 Found - 리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있다.
    * 307 Temporary Redirect - 302와 기능은 같지만, 리다이렉트시 요청 메서드와 본문 유지
    * 303 See Other - 302와 기능은 같지만, 리다이렉트시 요청 메서드가 GET으로 변경된다.
  * 특수 리다이렉션
    * 304 Not Modified
    * 캐시 목적으로 사용
    * 클라이언트에게 리소스가 수정되지 않았음을 알려준다.
    * 로컬PC의  캐시를 재사용하기 때문에 메세지 바디를 포함하지 않는다.
    * 조건부 GEt, HEad 요청시 사용한다.
* 4xx(Client Error): 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없다
  * 400 Bad REquest
  * 401 Unauthorized - 인증되지 않음(로그인)
  * 403 Forbidden - 서버가 요청을 이해했지만 승인을 거부함
    * 주로 인증 자격은 증명 했지만, 접근 권한이 불충분한 경우
  * 404 Not Found - 요청 리소스를 찾을 수 없거나, 클라이언트가 권한이 부족한 리소스에 접근할 때 리소스를 숨기고 싶을 때 사용한다.
* 5xx(Server Error): 서버 오류, 서버가 정상 요청을 처리하지 못함
  * 500 Internal Server Error - 서버 문제로 오류 발생, 애매하면 500 오류로 사용
  * 503 Service Unavailable - 서비스 이용 불가

### 리다이렉션
* 영구 리다이렉션 - 특정 리소스의 URI가 영구적으로 이동
  * ex) /event -> /new-event
* 일시 리다이렉션 - 일시적인 변경
  * 주문 완료 후 주문 내역 화면으로 이동
  * PRG: Post/Redirect/Get
* 특수 리다이렉션
  * 결과 대신 캐시를 사용
 
 PRG를 사용해서 새로코침 했을때 중복주문을 들어가는 것을 막을 수 있다.
 ![POST img](https://mblogthumb-phinf.pstatic.net/MjAyMjAzMjVfMTMy/MDAxNjQ4MjA4Mzc1NjA2.cTB02Ha4fSzVo0cbESmdZOPWeSV-J0MHFwdKoe4btd8g.twrTxfMYnUbHOSQ8UvuYm5_dkkNh_Plxvyqyl0UJSUwg.PNG.fbfbf1/image.png?type=w800)
 ![PRG Redirection imp](https://mblogthumb-phinf.pstatic.net/MjAyMjAzMjVfMTM4/MDAxNjQ4MjA4NTIwODcz.WTVVuYUgTJz7bjASF3SfBpcCx0caPGtVpaQ7WVtvdY8g._BGPXODRD6Ed9lM0NLTwF9JYXM-u4zR0M79NpSMMxYYg.PNG.fbfbf1/image.png?type=w800)
 

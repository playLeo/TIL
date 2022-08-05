
![HTTP Message 구조2](https://backtony.github.io/assets/img/post/http/2-4.PNG)
![HTTP Message 구조](https://velog.velcdn.com/images%2Fanhesu11%2Fpost%2Ff9934c03-a615-41ff-8862-219629fb0aee%2Fimage.png)

## Request 메세지 구조
HTTP 요청은 크게 세 부분으로 구성되어있다.
1. Request Line : 요청의 첫 번째 줄에 해당한다. 
  1. HTTP Method : 해당 요청이 의도한 액션을 정의하는 부분. 주로 GET, POST, DELETE, PUT, PATCH 가 많이 쓰인다.
  2. Request Target : 해당 request가 전송되는 목표 url
  3. HTTP Version : 사용되는 HTTP 버전
2. Headers : 해당 요청에 대한 추가 정보(메타 데이터)를 담고있다.
Key: Value 값으로 되어있다.

자주사용되는 Headers

3. Body : 해당 요청의 실제 내용.

## Response 메세지 구조
1. Status Line : 응답의 상태 줄.
  1. HTTP Version : 요청의 HTTP 버전과 동일
  2. Status Code : 응답 메세지의 상태 코드(200:성공, 400:클라이언트 요청 오류, 500:서버 내부 오류)
  3. Status Text : 응답 메세지의 상태를 간략하게 설명해주는 텍스트
2. Headers : 요청의 헤더와 동일


### HTTP 주요 메서드
* GET: 리소스 조회
  * 서버에 전달하고 싶은 데이터는 query를 통해 전달.
  * 메세지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아 권장하지 않는다.
* POST: 요청 데이터 처리, 주로 등록에 사용
  * 대상 리소스가 리소스의 고유 한 의미 체계에 따라 요청에 포함 된 표현을 처리하도록 요청한다.
  * 메세지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행한다.
  * 회원가입, 주문, 글쓰기, 댓글 달기, 신규 주문생성, 기존문서에 내용추가 등등...
  * 단순히 데이터를 생성하거나,  변경하는 것을 넘어서 프로세스를 처리해야 하는 경우 사용한다.
  * PSOT/orders/{orederId}/start-delivery(컨트롤 URI) - 리소스 위주로 설계해야 하지만 어쩔수 없는 경우는 컨트롤 URI를 사용한다.
* PUT: 리소스를 대체, 없으면 생성
  * **클라이언트가 리소스를 식별**
* PATCH: 리소스 부분 변경
* DELETE: 리소스 삭제
* HEAD: GET과 동일하지만 메세지 부분을 제외하고, 상태 줄과 헤더만 반환

### HTTP 메서드의 속성
* 안전(Safe Methods) - 호출해도 리소스를 변경하지 않는다.(GET)
* 멱등(Idempotent Methods) - f(f(x)) = f(x) n번을 호출해도 결과가 같다. (단 n>=1) (GET, PUT, DELETE)
  * 비멱등 - POST: 결제같은 사항, PATCH: 값을 변경할 때, 값이 누적될 경우 비멱등
* 캐시가능(Cacheable Methods) - 응답결과를 캐시에서 사용하는 것(GET, HEAD, POST, PATCH)
  * 실제로는 GET, HEAD 정도만 캐시로 사용

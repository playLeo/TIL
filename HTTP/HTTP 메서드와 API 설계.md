## HTTP 주요 메서드
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

## HTTP 메서드의 속성
* 안전(Safe Methods) - 호출해도 리소스를 변경하지 않는다.(GET)
* 멱등(Idempotent Methods) - f(f(x)) = f(x) n번을 호출해도 결과가 같다. (단 n>=1) (GET, PUT, DELETE)
  * 비멱등 - POST: 결제같은 사항, PATCH: 값을 변경할 때, 값이 누적될 경우 비멱등
* 캐시가능(Cacheable Methods) - 응답결과를 캐시에서 사용하는 것(GET, HEAD, POST, PATCH)
  * 실제로는 GET, HEAD 정도만 캐시로 사용
  
## 클라이언트에서 서버로 데이터 전송
* 정적 데이터 조회(이미지, 텍스트) - GET
* 동적 데이터 조회(정렬 필터) - GET(쿼리 파라미터 사용)
* HTML Form을 통한 데이터 전송
  * Content-Type:application/x-www-form-unlencoded - enctype 디폴트로 전송시
  * Content-Type:multipart/form-data;boundary=----XXXX - enctype="multipart/form-data" (파일 업로드 같은 바이너리 데이터 전송시 사용)
* HTTP API를 통한 데이터 전송
  * 서버 to 서버, 앱 클라이언트, 웹 클라이언트(AJAX)
  * Content-Type:application/json을 주로 사용(사실상표준)

## HTTP API 설계 예시
put기반 등록과 post기반 등록으로 2가지로 나눌 수 있다.(주로 post 기반)

회원 관리 시스템
* 회원 목록 /members -> GET
* 회원 등록 /members -> POST
* 회원 조회 /members/{id} -> GET
* 회원 수정 /members/{id} -> PATCH, PUT, POST
* 회원 삭제 /members/{id} -> DELETE

파일 관리 시스템
* 파일 목록 /files -> GET
* 파일 등록 /files/{filename} -> PUT
* 파일 조회 /files/{filename} -> GET
* 파일 삭제 /files/{filename} -> DELET
* 파일 대량 등록 /files -> POST

POST로 리소스 등록(회원 등록)
* 클라이언트는 등록될 리소스의 URI를 모른다.
* 서버가 새로 등록된 리소스 URI를 생성해주고 넘겨준다.
* 컬렉션(Collection)
  * 서버가 관리하는 리소스 디렉토리
  * 서버가 리소스의 URI를 생성하고 관리
  * 여기서 컬렉션은 /members

PUT으로 리소스 등록(파일 등록)
* 클라이언트가 리소스 URI를 알고 있어야 한다.(등록할 파일을 지정해야 하기 때문)
* 클라이언트가 직접 리소스의 URI를 지정한다.
* 스토어(Store)
  * 클라이언트가 관리하는 리소스 저장소
  * 클라이언트가 리소스의 URI를 알고 관리
  * 여기서 스토어는 /files
  
  
**정리**
참고하면 좋은 URI 설계 개념
* 문서(document)
  * 단일 개념(파일 하나, 객체 인스턴스, 데이터베이스 row)
  * ex)/members/100, fildes/star.jpg
* 컬렉션(collection) 
* 스토어(store)
* 컨트롤러(controller), 컨트롤 RUI
  * 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행
  * 동사를 직접 사용
  * ex) /members/{id}/delete

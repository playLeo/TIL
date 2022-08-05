
![HTTP Message 구조2](https://backtony.github.io/assets/img/post/http/2-4.PNG)
![HTTP Message 구조](https://velog.velcdn.com/images%2Fanhesu11%2Fpost%2Ff9934c03-a615-41ff-8862-219629fb0aee%2Fimage.png)

## Request 메세지 구조
HTTP 요청은 크게 세 부분으로 구성되어있다.
1. Request line : 요청의 첫 번째 줄에 해당한다. 
  1. HTTP Method : 해당 요청이 의도한 액션을 정의하는 부분. 주로 GET, POST, DELETE가 많이 쓰인다.
  2. Request target : 해당 request가 전송되는 목표 url
  3. HTTP Version : 사용되는 HTTP 버전
2. Headers : 해당 요청에 대한 추가 정보(메타 데이터)를 담고있다.
Key: Value 값으로 되어있다.

자주사용되는 Headers

3. Body : 해당 요청의 실제 내용.


## Response 메세지 구조
1. Status line : 응답의 상태 줄.
  1. HTTP version : 요청의 HTTP 버전과 동일
  2. Status code : 응답 메세지의 상태 코드(200:성공, 400:클라이언트 요청 오류, 500:서버 내부 오류)
  3. Status text : 응답 메세지의 상태를 간략하게 설명해주는 텍스트
2. Headers : 요청의 헤더와 동일

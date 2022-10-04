# 도커 이미지 생성 순서

Dockerfile 작성 -> 도커 클라이언트 -> 도커 서버 -> 이미지 생성

## Dockerfile
Dockerfile은 코드의 형태로 인프라를 구성하는 방법을 텍스트 형식으로 저장해 놓은 파일. Docker image를 만들기 위한 설정 파일

* **FROM : 베이스 이미지 설정**
* MAINTAINER : 관리자 정보
* **RUN : Shell Script 또는 명령 실행**
* **CMD : 컨테이너가 실행되었을 때 명령 실행**
* LABEL : 라벨 작성
* EXPOSE : 호스트와 연결할 포트 번호 설정
* ENV : 환경 변수 설정
* ADD : 파일, 디렉토리 추가
* COPY : 파일 복사
  * 베이스 이미지 스냅샷은 임시 컨테이너에 올라가지만 추가적인 종속성은 올라가지 않아 COPY를 통해 올려준다.
* ENTRYPOINT : 컨테이너가 시작되었을 때 스크릅트 실행
* VOLUME : 볼룸 마운트
* USER : 명령 실행할 사용자 권한 지정
* WORKDIR : RUN, CMD, ENTRYPOINT 명령이 실행될 작업 디렉토리
  * WORKDIR을 설정하지 않고 사용하면 ROOT 디렉토리에 COPY한 파일이 생성되면서 이름이 같은경우 덮어써지는 가능성이 있다. 또한 관리 차원에서 디렉토리를 만들어 관리하면 편하다.
* ARG : Dockerfile 내부 변수
* ONBUILD : 다른 이미지의 Base Image로 쓰이는 경우 실행될 명령
* SHELL : Default Shell 지정


베이스 이미지란?

도커 이미지는 여러개의 레이어로 되어 있다.

그중 베이스 이미지는 이미지의 기반이 되는 부분(OS라 생각하면 된다)

## docker build ./ or docker build .

도커 파일에 입력된 것들이 도커 클라이언트에 전달되어 도커 서버가 인식할 수 있도록 build 해준다.

* 만든 이미지에 이름을 설정

docker build -t 도커아이디/이름:버전 ./

___

spring boot docker ec2 배포
Q. base image에 jdk 올리는데 ec2의 ubuntu 위에 jdk는 왜 설치한는 거지 ?

https://velog.io/@18k7102dy/Docker-Spring-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EB%A5%BC-Docker%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%B4%EC%84%9C-%EB%B0%B0%ED%8F%AC%ED%95%B4%EB%B4%85%EC%8B%9C%EB%8B%A4

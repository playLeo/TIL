# Docker image 내부 파일 구조 보기

## docker run alpine ls

* docker - docker 클라이언트 언급
* run - 컨테이너 생성 및 실행
* alpine - 컨테이너를 위한 이미지
* ls - 이미지가 가지고 있는 **시작 명령어를 무시하고 커맨드 실행**(ls 커맨드는 현재 디렉토리의 파일 리스트 표출)

실행시 bin, dev, etc ... 디렉토리가 표시된다.

## docker run hello-world ls

```
C:\Users\yggb1>docker run hello-world ls
docker: Error response from daemon: failed to create shim task: OCI runtime create failed: runc create failed: unable to start container process: exec: "ls": executable file not found in $PATH: unknown.
```

실행 오류가 나는 이유는 alpine 이미지 파일 스냅샷안에 이미 ls를 사용 가능하게 하는 파일이 있어 사용 가능하지만,
hello-world 이미지 스냅샷에는 없다.

# 컨테이너 나열하기

## docker ps(process status) : 실행중인 컨테이너
* -a(all) : 모든 컨테이너 나열
* --format '{{.Names}}\t{{.Image}}' : 원하는 항목만 표시

1. CONTAINER ID - 컨테이너의 고유한 아이디 해쉬값(일부만 표시됨)
2. IMAGE - 컨테이너 생성시 사용한 도커 이미지
3. COMMAND - 컨테이너 시작시 실행될 명령어(대부분 이미지에 내장되어 있어 별도 설정 X)
4. CREATED - 컨테이너가 생성된 시간
5. STATUS - 컨테이너의 생태(실행중 : Up, 종료 : Exited, 일시정지 : Pause) 
6. PORTS - 컨네이너가 개방한 포트와 호스트에 연결한 포트(설정하지 않는 경우 출력되지 않는다)
7. NAMES - 컨테이너 고유 이름(--name 옵션으로 설정하지 않으면 도커 엔진이 임의로 만든다, id와 마찬가지로 중복 안됨)
* docker rename original-name changed-name 으로 변경 가능

# Docker 컨테이너의 생명주기

<img src="https://velog.velcdn.com/images%2Frmswjdtn%2Fpost%2Fa84f85ba-1efd-4cc4-807e-e30e46ab9375%2Fimage.png" width="100%" height="80%">

* docker create : 컨테이너에 할당된 하드디스크에 파일 스냅샷을 올린다.
* docker start : 시작시 실행 될 명령어 실행
* docker run : create + start
* -a(attach) : container가 실행될 때 나오는 output을 모두 terminal에 출력
* docker stop : 실행중인 container를 Gracefully하게 중지시킨다.(진행중인 작업 완료 후 중지)
* docker kill : 실행중인 container를 바로 중지시킨다.
* docker rm : 중지된 container를 삭제한다.(모든 컨테이너 삭제 - docker rm `docker ps -a -q`)
* docker system prune : 컨테이너, 이미지, 네트워크 모두 삭제(실행중인 컨테이너에는 영향을 주지 않는다.)

# 실행중인 컨테이너에 명령어 전달

## docker exec

* docker를 이용해 redis 사용해보기
1. docker run redis
2. 새로운 터미널로 docker exec -it 컨테이너이름 redis-cli (-it는 interactive terminal이 합쳐진 옵션이고, redis-cli를 실행하고 터미널을 유지하기 위한 옵션)

* 컨테이너 안의 쉘 환경으로 접속
docker run -it 이미지이름 sh

* 쉘 터미널 환경에서 나오려면 Ctrl + D

# 포트

컨테이너 내부포트와 컨테이너 외부포트를 연결

docker run -p 5000:8080 이미지이름

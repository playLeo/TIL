## docker run alpine ls 해석

* docker - docker 클라이언트 언급
* run - 컨테이너 생성 및 실행
* alpine - 컨테이너를 위한 이미지
* ls - 이미지가 가지고 있는 **시작 명령어를 무시하고 커맨드 실행**(ls 커맨드는 현재 디렉토리의 파일 리스트 표출)

실행시 bin, dev, etc ... 디렉토리가 표시된다.

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

# docker run

## 기본 포멧
> $ docker run (<옵션>) <이미지 식별자> (<명령어>) (<인자>)

## -d 옵션
많은 경우 컨테이너를 백그라운드에서 실행해야 한다. -d 옵션을 사용해 컨테이너가 detached 모드에서 실행되게 하고, 실행 결과로는 컨테이너 ID만을 출력한다.

백그라운드로 실행하지 않으면 해당 터미널을 종료하면 컨테이너가 종료된다.

## -it 옵션
-i 옵션과 -t 옵션은 같이 쓰이는 경우가 매우 많다. 두 옵션은 컨테이너를 종료하지 않은체로, 터미널의 입력을 계속 컨테이너로 전달하기 위해 사용한다. 따라서 컨테이너 쉘이나 CLI 도구를 사용할 때 매우 유용하게 사용한다.

## --name 옵션
Docker 컨테이너에 이름을 부여한다.

## -e 옵션
Docekr 컨테이너의 환경변수를 설정하기 위해 사용한다. -e 옵션을 사용하면 Dockerfile의 ENV 설정도 덮어써지게 된다.

아래 커맨드는 FOO 환경 변수를 bar로 세팅하고, 환경 변수를 출력하고 있다.
> $ docker run -e FOO=bar python:3.8-alpine env

## -p 옵션

호스트와 컨테이너 간의 포트 배포/바인드를 위해 사용된다. 호스트 컴퓨터에서 컨테이너에서 리스닝하고 있는 포트로 접속할 수 있도록 설정해준다.

## -v 옵션
호스트와 컨테이너 간의 볼륨설정을 위해서 사용한다. 호스트 컴퓨터의 파일 시스템의 특정 경로를 컨테이너의 파일 시스템의 특정 경로와 마운트한다.

아래 커맨드는 호스트 컴퓨터의 현재 디렉토리를 컨테이너의 /etc경로로 마운트한 후, 그 안의 text.txt 파일의 내용을 출력하고 있다.
> docker run -v `pwd`:/etc python:3.8-alpine cat /etc/test.txt

## -w 옵션
Dockerfile의 WORKDIR 설정을 덮어쓰기 위해 사용한다.

## -entrypoint 옵션
Dockerfile의 ENTRYPOINT 설정을 덮어쓰기 위해서 사용한다.

## --rm 옵션
컨테이너를 일회성으로 실행할 때 주로 사용한다. 컨테이너가 종료될 때 컨테이너와 관련된 리소스(파일 시스템, 볼륨)까지 제거된다.

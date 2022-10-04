# Docker란?

도커는 리눅스의 컨테이너라는 기술(LXC)를 이용한 소프트웨어로 지금은 자체라이브러리인 libcontainer를 사용하고 있는 컨테이너 기반 가상화 도구이다.

윈도우에서 hyper-v를 통해 WSL2 기능을 사용하여 Linux환경을 사용하는거다.(WSL1은 hyper-v기반 X)


## 도커를 사용하는 이유
1. 개발환경에 구애받지 않고 손쉽게 내가 사용하던 개발환경을 구축할 수 있다.
2. 여러개의 독립된 프로세스를 띄워 마이크로서비스가 가능해진다.
3. 배포가 쉽고 수정도 쉽다.
4. 일반적인 Hypervisor가상화 방식과는 다르게 경량 가상화로 실행이 빠르다. 그로인해 여러개의 마이크로서비스가 가능해 불필요한 서버 확장이 필요 없다.
5. 여러개의 프로세스(컨테이너)관리가 쉬워진다.



## 컨테이너란? (LXC)

리눅스의 시스템 기능인 namespace와 cgroup 기반으로 만들어진 격리된 공간이다.

namespace는 프로세스를 독립시켜주는 가상화 기술이다. 즉 프로세스 ID(PID)가 같아도 서로 다른 프로세스다. - 같은 커널에서 동작하지만 서로 다른공간.

프로세스간의 통신은 IP로 해야한다.

격리된 공간(프로세스)를 만들고 나면 cgroup을 사용하여 하드웨어 자원을 분배한다.

## 컨테이너의 격리 환경 및 동작 원리
![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdiOvdN%2Fbtq6AvHVKbt%2FmF2hsL9jsGWIKfekItKbV1%2Fimg.png)


CLI 입력 -> 도커 클라이언트 -> 도커 서버 (이미지 캐쉬 확인 후 없으면 허브에서 pull) -> 도커 클라이언트 -> CLI terminal

<img src="https://miro.medium.com/max/640/0*6B7wOmE9I5RDvqMW.png" width="50%" height="50%">

docker run hello-world 실행시
```
C:\Users\yggb1>docker run hello-world
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
2db29710123e: Pull complete
Digest: sha256:62af9efd515a25f84961b70f973a798d2eca956b1b2b026d0a4a63a3b0b6a3f2
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

<img src="https://docs.docker.com/engine/images/architecture.svg" width="70%" height="50%">

Docker Client와 Docker Daemon은 REST API를 사용하여 통신하며, Docker Daemon이 컨테이너를 구축, 실행 및 배포를 한다.

Docker Client와 Docker Daemon은 동일한 시스템에서 실행될 수도 있고, 원격으로 연결하여 사용할 수도 있다. 

**Docker Daemon**

Docker Daemon은 Client로 부터 API 요청을 수신하고 Image, Container, Network 및 Volume과 같은 Docker Object를 관리한다. Daemon은 Docker 서비스를 관리하기 위해 다른 Daemon과 통신할 수 있다.

**Docker Client**

Docker Client는 사용자가 Docker Daemon과 통신하는 주요 방법이다. docker 명령여넌 Docker API를 사용하며, Docker Client는 둘 이상의 Docker Daemon과 통신 할  수 있다.

**Docker Registry**

Docker Registry는 Docker Image를 저장한다. Docker Hub는 누구나 사용할 수 있는 Public Registry이며, Docker는 기본적으로 Docker Hub에서 Image를 찾아서 Container를 구성하도록 되어 있다. 개개인이 구성하는 Registry도 만들 수 있다.

**Docker Object**

Docker Object는 Docker Daemon에 의해 생성 및 관리되는 Image, Container, Network, Volume 등의 개체를 말한다.

**Image**

Image는 Docker Container를 생성하기 위한 일기 전용 Template이다. Image들은 다른 Image 기반 위에 Customizing이 추가되어 만들어질 수 있으며, Dockerfile에 Image를 만들고 실행하는 데 필요한 단계를 명령어로 정의하여 생성한다. Docekrfile에 정의된 각각의 명령어들은 Image Layer를 생성하며, Layer들이 모여 Image를 구성한다. Dockerfile을 변경하고 Image를 다시 구성하면 변경된 부분만 새로운 Layer가 생성된다. 이러한 Layer 구조는 Docker가 타 가상화 방식과 비교할 때, 매우 가볍고 빠르게 기동할 수 있는 요인이 된다.

**Container**

Container는 Docker API를 사용하여 생성, 시작, 중지, 이동, 삭제 할 수 있는 Image의 실행가능한 Instance를 나타낸다. Container를 하나 이상의 Network에 연결하거나, 저장 장치로 묶을 수 있으며, 현재 상태를 바탕으로 새로운 Image를 생성할 수도 있다. 기본적으로 Container는 Host 또는 다른 Container로부터 격리되어 있으며, Network/Storage와 다른 하위 시스템에 대한 접근을 직접 제어할 수 있다. Container가 제거될 때는 영구 저장소에 저장되지 않은 변경사항은 모두 해당 Container와 같이 사라진다.

**Service**

Service를 사용하면, 여러 개의 Docker Daemon들로 이루어진 영역 내에서 Container들의 확장(Scaling)시킬 수 있다. Srvice는 특정 시간동안 사용 가능한 Service의 Replica 개수와 같은 상태 정보들을 직접 정의할 수 있다. 기본적으로 Service는 Docker Daemon들 간의 Load Balancing을 제공하고 있기 때문에, 사용자 관점에서는 단일 Application으로 보인다.


### image - 프로그램을 실행하는데 필요한 설정이나 종속성
1. 시작시 실행 될 명령어
2. 파일 스냅샷

이미지를 통해 컨테이너를 만들면 파일 스냅샷을 기반으로 필요한 프로그램을 cgroup으로 할당 된 하드부분에 설치 후 시작 명령어 실행

## container 기술을 linux container 기술인데 docker와 lxc의 차이점은 무엇이가 ?

![img](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99364B495DD2BF3D21)

1. LXC는 하나의 컨테이너에 여러개의 응용프로그램들을 띄울 수 있다. 하지만 도커는 컨테이너당 하나의 응용프로그램을 사용하길 권장한다.
    1. 컨테이너당 응용프로그램을  하나씩 사용하는 이유
    2. 다른 프로젝트에서 쉽게 재사용 가능
    3. 보안 및 격리 관점에서 더 많은 유연성을 가져올 수 있다.
    4. 업데이트시 서로간의 간섭을 받지 않는다.
    5. 마이크로서비스 아키텍처를 구성하는것에 대해 효율적이다. 
2. Docker에는 캡쳐기능이 있다. VM의 스냅샷과 동일하다.
3. Docker는 컨테이너를 이미지화 시켜 배포를 쉽고 빠르게 할 수 있다. 

## Docker 다운로드(Windows 환경)
1. 공식 홈페이지에서 Windows docker 설치
2. WSL 2 installation is incomplete. 오류 발생시 -> 관리자 권한으로 파워쉘 실행후
    1. dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart (리눅스 서브시스템 활성)
    2.  dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart(가상 머신 플랫폼 기능 활성)
    3.  wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi(WSL 2 설치)

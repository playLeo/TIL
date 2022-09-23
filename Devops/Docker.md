# Docker란?

도커는 리눅스의 컨테이너라는 기술(LXC)를 이용한 소프트웨어로 지금은자체라이브러리인 libcontainer를 사용하고 있다.

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

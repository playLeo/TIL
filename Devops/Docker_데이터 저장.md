Docker 컨테이너에 쓰여진 데이터는 기본적으로 컨테이너가 삭제될 때 함께 사라지게 된다. Docker에서 돌아가는 많은 애플리케이션이 컨테이너의 생명 주기와 관계없이
데이터를 영속적으로 저장해야하고, 여러 개의 Docker 컨테이너가 하나의 저장 공간을 공유해서 데이터를 읽고 써야 한다.

이렇게 Docker 컨테이너의 생명 주기와 관계없이 데이터를 영속적으로 저장할 수 있도록 Docker는 세가지 옵션을 제공한다.

1. Docker Volume(권장)
2. Bind Mount
3. tmpfs

<img src="https://docs.docker.com/storage/images/types-of-mounts.png" width="60%" height="50%">

**Volume**

* Volume은 Docker가 관리하는 Host File System의 일부에 Data가 저장된다.
* Non-Docker 프로세스들이 File System의 해당 부분을 수정해서는 안된다.

**Bind Mount**
* Bind Mount는 Host System의 어디든지 Data가 저장될수 있다.
* 저장되는 Data는 System File이거나 Directory일 수 있다.
* Docker Host 또는 Docker Container의 Non-Docker 프로세서들이 언제든지 저장된 Data를 수정할 수 있다.

**tmpfs**
* tmpfs moun는 Host System의 Memory에만 Data가 저장되며, 절대로 Host의 File System에 저장되지 않는다.

## 볼륨 생성 및 조회

```
# sample-vol 볼륨 생성
docker volume create sample-vol

# 볼륨 조회
docker volume ls

# 볼륨 상세보기
docker volume inspect sample-vol
```

## 컨테이너에 볼륨 마운트하기

-v 옵션을 사용해 마운트할 볼륨명:컨테이너 내의 경로 or 호스트 디렉토리 경로:컨테이너 내의 경로

A) 도커 볼륨 생성 후 연결(추천)
> docker run -v my_volume:/my_data...

호스트 /var/lib/docker/bolumes/ 하위에 볼륨이름으로 디렉토리를 생성하고 컨테이너에서 연결하여 사용.

볼륨 단위로 이름을 부여하고 사용처를 관리할 수 있다.

B) 호스트 디렉토리를 직접 연결
> docker run -v /home/test/data:/my_data

호스트 디렉토리에 직접 컨테이너를 연결하여 사용

각자의 장단점이 있지만 일반적으로 도커 볼륨을 생성 후 연결한다.

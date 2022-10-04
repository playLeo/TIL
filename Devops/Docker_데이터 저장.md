Docker 컨테이너에 쓰여진 데이터는 기본적으로 컨테이너가 삭제될 때 함께 사라지게 된다. Docker에서 돌아가느 많은 애플리케이션이 컨테이너의 생명 주기와 관계없이
데이터를 영속적으로 저장해야하고, 여러 개의 Docker 컨테이너가 하나의 저장 공간을 공유해서 데이터를 읽고 써야 한다.

이렇게 Docker 컨테이너의 생명 주기와 관계없이 데이터를 영속적으로 저장할 수 있도록 Docker는 두가지 옵션을 제공한다.

1. Docker Volume(권장)
2. Bind Mount

<img src="https://docs.docker.com/storage/images/types-of-mounts.png" width="60%" height="50%">

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

-v 옵션을 사용해 마운트할 볼륨명:컨테이너 내의 경로

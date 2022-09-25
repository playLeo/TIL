# JVM이란?

JVM이란 Java Virtual Machine의 약자로, 자바와 운영체제 사이에서 중개자 역할을 수행하며, 자바가 운영체제에 구애 받지 않고  프로그램을 실행할 수 있도록 도와준다. 
또한, 가비지 컬렉터를 사용한 메모리 관리도 자동으로 수행하며, 다른하드웨어와 다르게 레지스터 기반이 아닌 스택 기반으로 동작한다.




# JVM 구조

<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcQRqku%2Fbtru0vJ6Ixx%2F9qCTW7ChXc80fGfQUrT4B0%2Fimg.png" width="500" height="400"/>

1. Class Loader - 클래스 파일을 참조하는 순간 동적으로 읽어서 메모리에 로드되면서 JVM에 링크 된다.
2. Execution Engine - 클래스 로더를 통해 JVM 내의 Runtime Data Area에 배치된 바이트 코드들을 명령어 단위로 읽어서 실행한다. JIT는 바이트 코드를 어셈블러 같은 네이티브 코드로 바꿈으로서 씰행을 빠르게 하지만 변환에 비용이 발생해 인터프리터 방식을 사용하다가 일정한 기준을 넘어가면 JIT 컴파일러 방식으로 실행한다.
3. Garbage Collector - 힙 메모리 영역에 생성된 객체들 중에서 참조되지 않은 객체들을 탐색 후 제거하는 역할을 수행한다.
4. Runtime Data Area - JVM의 메모리 영역으로 자바 애플리케이션을 실행할 때 사용되는 데이터들을 적재하는 영역이다.
    1. Method area - 모든 쓰레드가 공유하는 메모리 영역. 클래스, 인터페이스, 메소드, 필드, Static 변수 등의 바이트 코드 보관.
    2. Heap area - 모든 쓰레드가 공유하며, new 키워드로 생성된 객체와 배열이 생성되는 영역. 메소드영역에서 로드된 클래스만 생성이 가능하고 GC를 수행하는 영역.
    3. Java Stack area - 메서드 호출 시마다 각각의 스택 프레임을 생성한다. 메서드 안에서 사용되는 값등을 저장하고, 호출된 메서드의 매개변수, 지역변수, 리턴 값 및 연산 시 일어나는 값들을 임시로 저장한다. 메서드 수행이 끝나면 프레임별로 삭제한다.
    4. PC Register - 쓰레드가 시작될 때 생성되며, 쓰레드 마다 하나씩 존재한다. 쓰레드가 어떤 부분을 무슨 명령으로 실행해야할 지에 대한 기록을 하는 부분으로 현재 수행중인 JVM명령의 주소를 갖는다.
    5. Native method stack - 자바 외 언어로 작성된 네이티브 코드를 위한 메모리 역역.

<img src="https://t1.daumcdn.net/cfile/tistory/992EE9465D08E9B903" width="40%" height="30%"/>

___

# Garbage Collection이란?
Garbage Collection이란 메모리 관리 기법 중의 하나로, 프로그램이 동적으로 할당했던 메모리 영역 중에서 필요없게 된 영역을 해제하는 기능.

<img src="https://velog.velcdn.com/images%2Frecordsbeat%2Fpost%2F682408fc-f29e-42e9-b980-3d6f1d6c4989%2Fimage.png" width="60%" height="50%">

* java8 부터 Perm영역은 제거되었다.

* eden, survivor0, survivor1, old 영역을 옮길 때마다 Age값을 증가시킨다.
* young 영역에서 특정 Age값 이상이 되어 old 영역으로 넘어 가는 것을 Promotion이라 한다.


**young 영역**
* 새롭게 생성된 객체가 할당(Allocation)되는 영역. young 영역에서 수행되는 GC를 Minor GC라고 부른다.
    * eden - young 영역중에서도 특히 방금 막 생성된 객체가 할당되는 영역
    * survivor - eden에서 reachable한 객체들이 할당되는 영역

**old 영역**
* youn영역에서 reachable 상태를 유지하며 특정 특정 Age값 이상인 객체가 할당되는 영역
* old 영역에서 수행되는 GC를 Major GC 또는 Full GC라고 부른다.

## GC 동작 알고리즘
<img src="https://velog.velcdn.com/images%2Frecordsbeat%2Fpost%2F55ab1fa7-3a87-4859-ac87-03a35bdcd9b1%2Fimage.png" width="55%" height="40%">

1. Reference Counting - 참조하는 수를 계산해 메모리를 해제한다. 순환참조를 해결할 수 없는 단점이 있다.
<img src="https://velog.velcdn.com/images%2Frecordsbeat%2Fpost%2F12cd90e8-2a6e-474a-b79e-ce50dd2bbe89%2Fimage.png" width="45%" height="30%">

2. Mark and Sweep - threa의 stack, method area 등 heap 영역 참조가 가능한 영역을 Root Set이라 하고 Root Set부터 참조상태를 확인하고(Mark) unreachable한 객체를 해제한다(Sweep).
<img src="https://velog.velcdn.com/images%2Frecordsbeat%2Fpost%2F40b78c47-247d-428b-a482-065116b2d6c2%2F0_-dB_3FTm5N-5kjN6.gif" width="45%" height="30%">

* Stop the World
가비지 컬렉션을 실행하기 위해 JVM이 애플리케이션의 실행을 멈추는 작업이다. GC가 실행될 때는 
GC를 실행하는 쓰레드를 제외한 모든 쓰레드들의 작업이 중단되고, GC가 완료되면 작업이 재개된다.
GC의 성능 개선을 위해 튜닝을 한다고 하면 보통 stop-the-world의 시간을 줄이는 작업을 하는 것이다.


https://junghyungil.tistory.com/133 - memory leak 정리
https://techblog.woowahan.com/2628/ - memory leak 해결
https://jithub.tistory.com/300 - heap dump 

# Memory Leak
CS 의미로 살펴볼 때, 컴퓨터 프로그램이 필요하지 않은 메모리를 계속 점유하고 있는 현상을 의미하고 자바에서는 더이상 사용되지 않는 객체들이 GC에 의해 회수되지 않고 계속 누적되는 현상을 말한다.

GC가 되지 않는 루트 참조 객체는 크게 3가지다.
1. Static 변수에 의한 객체 참조
    *  static은 GC에 대상이 되지 않는다. Static변수는 클래스가 생성될 때 메모리를 할당 받고 프로그램이 종료되는 시점에 반환되므로 사용하지 않고 있어도 메모리가 있다.
2. 모든 현재 자바 슬레드 스택내의 지역 변수, 매개변수에 의한 객체 참조
    * 자바에서 현재 실행중인(각 스레드별로) 모든 메소드내에 선언된 지역 변수와 매개변수에 의해 참조되는 객체와 그 객체로부터 직간접적으로 참조되는 모든 객체는 참조되어 사용될 가능성이 있으며,  


**참조**
 * https://velog.io/@recordsbeat/Garbage-Collector-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EA%B8%B0
 * https://mangkyu.tistory.com/118
 * https://coding-factory.tistory.com/828

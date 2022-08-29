# 스트림(Stream)

자바 8에서 추가한 스트림은 람다를 활용할 수 있는 기술 중 하나다.
자바 8 이전에서는 배열 또는 컬렉션 인스턴스를 다루는 방법은 for 또는 foreach 문을 만들어서 하나씩 꺼내서 다루는 방법이었다.
코드의 양이 많아진다면 여러 로직이 섞이고, 메소드를 나눌 경우 루프를 여러 번 도는 경우가 발생한다.

스트림은 '데이터의 흐름'이다. 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있다.
또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있다. 즉, 배열과 컬렉션을 함수형으로 처리할 수 있다.

또 하나의 장점은 간단하게 병렬처리(multi-threading)가 가능하다는 점입니다. 하나의 작업을 둘 이상의 작업으로
잘게 나눠서 동시에 진행하는 것을 병렬처리라고 합니다. 즉 스레드를 이용해 많은 요소들을 빠르게 처리할 수 있다.

스트림에 대한 내용은 크게 세가지로 나눈다.
1. 생성하기 : 스트림 인스턴스 생성.
2. 가공하기 : 필터링(filtering) 및 맵핑(mapping)등 원하는 결과를 만들어가는 중간 작업
3. 결과 만들기 : 최종적으로 결과를 만들어내는 작업

전체 -> 맵핑 -> 필터링1 -> 필터링2 -> 결과 만들기 -> 결과물

### 생성하기
보통 배열과 컬렉션을 이용해서 스트림을 만들지만 이 외에도 다양한 방법으로 스트림을 만들 수 있다.

#### 배열 스트림

Arrays.stream()에 인자로 배열을 입력하면 배열을 순회하는 스트림 객체르 만들 수 있다.

```java
String[] arr = new String[]{"a", "b", "c"};
Stream<String> stream = Arrays.stream(arr);
Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 1~2 요소 [b, c]
```

#### 컬렉션 스트림
컬렉션 타입(Collection, List, Set)의 경우 인터페이스에 추가된 디폴트 메소드 stream을 이용해서 스트림을 만들 수 있다.

```java
public interface Collection<E> extends Iterable<E> {
  default Stream<E> stream() {
    return StreamSupport.stream(spliterator(), false);
  } 
  // ...
}
```

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream = list.stream();
Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
```

#### 빌더 스트림

```java
String<String> stream = Stream<String>builder()
                          .add("Apple")
                          .add("Banana")
                          .add("Melon")
                          .build();
```

#### 제너레이터 스트림
데이터를 생성하는 람다식을 이용해서 스트림을 생성한다.

getnerate() 메소드는 데이터를 무한대로 생성하는 스트림이 만들어 지기 때문에 limit을 줘야한다.
```java
Stream<String> stream = Stream.generate(() -> "Hello").limit(5)
```


#### Iterator 스트림
```java
Stream<String> stream = Stream.iterate(100, n -> n + 10).limit(5); // 100, 110, 120, 130, 140
```

#### Empty 스트림
```java
Stream<String> stream = Stream.empty();
```


_____

### 가공하기

#### Filter
```java
Stream<Integer> stream = IntStream.range(1, 10).boxed();
stream.filter(v -> ((v % 2) == 0))
            .forEach(System.out::println); // 2, 4, 6, 8
```

#### Map
```java
Stream<Integer> stream = IntStream.range(1, 10).boxed();
stream.filter(v -> ((v % 2) == 0))
            .map(v -> v * 10)
            .forEach(System.out::println); // 20, 40, 60, 80
```



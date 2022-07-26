# Spring Optional

NPE를 피하려면 null을 검사해야한다.


```java
List<String> names = getNames();
names.sort() //names가 null 이라면 NPE 발생

//NPE를 방지하기 위해 null 검사
if(names != null)
    names.sort();
```

### Optional

Java8에서 Optional<T> 클래스를 제공.
null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 값이 null이 와도 NPE가 발생하지 않고,
각종 메서드를 제공한다
```java
public final class Optional<T> {

  // If non-null, the value; if null, indicates no value is present
  private final T value;
}
```

Optional은 Wrapper 클래스이기 때문에 값이 없을 수도 있다.
```java
Optional<String> optional = Optional.empty();

System.out.println(optional); // Optional.empty
System.out.println(optional.isPresent()); // false
```

* Optional.of() - 값이 null이 아닌 경우
```java
Optional<String> optional = Optional.of("Hello");
//Optional.of로 null 을 저장하면 NPE 발생
```

* Optional.ofNullable() - null값 일 수도 있는 경우
```java
Optional<String> optional = Optional.ofNullable()
```

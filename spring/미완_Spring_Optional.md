# Optional

NPE를 피하려면 null을 검사해야한다.


```java
List<String> names = getNames();
names.sort() //names가 null 이라면 NPE 발생

//NPE를 방지하기 위해 null 검사
if(names != null)
    names.sort();
```
___


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

* ifPresent
    
    
    
### Optional 활용

1. Optional 변수에 null을 직접 할당하지 말자.
   
null을 다룰 수 있지만 직접적으로 null을 할당하는 것은 Optional의 도입 의도와 맞지 않는다.

내부값을 null로 초기화한 싱글톤객체 Optional.empty() 메서드를 사용하여 처리하자.
    
2. Optional.get() 호출 전, Optional 객체가 값을 가지고 있는지 확실하게 하자.
    
빈 객체에 Optional.get() 메서드를 실행하면 NoSuchElementException이 발생한다. 때문에 빈 객체인지 확인 해줘야한다.
    
if문을 통해 ifPresent()를 활용하기 보다 Optional의 API를 활용하여 더욱 간단하게 처리할 수 있다.
    
 ```java
Person person = findById(4).orElseThrow(PersonNotFoundException::new);
String name = person.getName();
```
    
3. 
    
    https://www.latera.kr/blog/2019-07-02-effective-optional/

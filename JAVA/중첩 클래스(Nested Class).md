# 중첩 클래스(Nested Class)

* 다음과 같이 다른 클래스 안에 정의된 클래스를 중첩 클래스라 한다.
```java
public calss TestClass{
  
  class NestedClass{
  }
  
  static class StaticNestedClass{
  }
}
```

* 위의 예제에서 NestedClass 선언시 "Inner class may be static" 경고가 발생한다.
>Reports any inner classes which may safely be made static. An inner class may be static if it doesn't reference its enclosing instance.
>
>A static inner class does not keep an implicit reference to its enclosing instance. This prevents a common cause of memory leaks and uses less memory per instance of the class.

번역하면 

> 위험 없이 static 으로 만들수 있는 내부 클래스를 발견했습니다. 내부 클래스가 자신의 바깥 클래스 인스턴스를 참조하지 않는다면, 내부 클래스는 static으로 선언해도 됩니다.
> 
> static 내부 클래스는 자신의 바깥 클래스 인스턴스의 임시적 참조를 유지하지 않습니다. 즉 static 내부 클래스로 선언하면 메모리 누수의 일반적인 원인을 예방할 수 있고, 클래스의 각 인스턴스당 더 적은 메모리를 사용하게 됩니다.

### non-static nested class
* inner class라고 하면 외부 인스턴스에 대한 참조가 유지된다.
* 외부 인스턴스는 내부 클래스를 new를 통한 인스턴스 할당으로 멤버변수처럼 사용할 수 있다.
* 외부에 대한 참조가 유지되면서 내부 클래스도 외부 클래스의 자원을 사용할 수 있다.

### static nested class
* static의 특징에 따라 외부 인스턴스 멤버의 직접참조가 불가능하다.

### 결론
* 외부 참조가 유지된다는 것은 메모리에 대한 참조가 유지되고 있다는 뜻이고, GC가 메모리를 회수할 수 없다. 이는 메모리 누수를 부르는 치명적 결과를 초래한다.
* 항상 외부 인스턴스의 참조를 통해야  하므로 성능 상 비효율 적이다.
* 외부 인스턴스에 대한 참조가 필요하지 않다면 static nested class로 사용하자. 


# Java Lambda

### 람다함수 - 익명 함수를 지칭하는 용어

익명함수란 이름없는 함수. 익명함수들은 공통으로 일급객체(First Class Citizen)라는 특징을
가지고 있다.
일급객체란 일반적으로 다른 객체들에 적용 가능한 연산을 모두 지원하는 개체를 가르킨다.

자바에서 함수형 프로그래밍을 구현하는 방식, java8 부터 지원

함수형 프로그래밍(Functional Programming)

순수 함수(pure function)를 구현하고 호출함으로써 외부 자료에 부수적인 형향을 주지 않고 매개 변수만을 사용하도록 만든 함수

입력 받은 자료를 기반으로 수행되고 외부에 영향을 미치지 않으므로 병렬처리 가능, 안정적인 확장성 보장

* 장점
> * 코드가 간결해진다.
> * 지연연상을 수행하며 불필요한 연산을 최소화 할 수있다.
> * 멀티쓰레드를 활용하여 병렬처리를 할 수 있다.

* 단점
> * 호출이 까다롭다
> * 람다 stream 사용시 단순 for문, while문 성능 떨어진다.
> * 많이 사용시 가독성이 떨어진다.

함수를 변수처럼 사용하는 람다식
  ```java
  //FunctionalInterface 선언을 통해 메서드를 1개 제약조건을 컴파일에게 알린다.
//메서드가 2개 이상일 경우, 람다식의 값이 어느 메서드의 값인지 알 수 없기 때문인다.
@FunctionalInterface
interface interface_exam{
    public abstract int add(int a, int b);
}

public class Main {
    public static void main(String[] args) {

        //람다식
        //인터페이스 레퍼런스에 람다문법
        interface_exam lambda_exam = (x,y) -> x+y;

        System.out.print(lambda_exam.add(4, 6)); // 10

        //람다식 내부적으로는 익명 클래스를 만들어 작동된다.

        interface_exam exam = new interface_exam() {
            @Override
            public int add(int a, int b) {
                return a+b;
            }
        };
    }
}
  ```


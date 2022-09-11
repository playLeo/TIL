## JavaScript란 ?

JavaScript는 웹페이지에서 복잡한 기능을 구현할 수 있도록 하는 스크립팅 언어이다.

DOM API를 통해 HTML과 CSS를 동적으로 수정, 사용자 인터페이스를 업데이트하는 일에 가장 많이 사용된다.

주기적으로 갱신되거나, 사용자와 상호작용이 가능하거나, 애니메이션이 적용된 2D/3D 그래픽을 볼 수 있다면 JavaScript가 관여하고 있을거라고 생각해도 좋다.


## 문법

### 변수 - var

### 비교연산자 - '==='는 데이터 타입까지 같이야 한다.

```javascript
alert(null == undefined);       //true
alert(null === undefined);      //false
alert(true == 1);               //true
alert(true === 1);              //false
alert(true == '1');             //true
alert(true === '1');            //false
 
alert(0 === -0);                //true
alert(NaN === NaN);             //false
// NaN은 0/0과 같은 연산의 결과로 만들어지는 특수한 데이터 타입(숫자 X)
```

### 함수

```javascript
fuction backgroundSetColor(color){
  document.querySelector('body').style.backgroundColor = color;
}
```

* 변수를 함수로 정의가 가능하다.

```javascript
var numbering = function (){
    i = 0;
    while(i < 10){
        document.write(i);
        i += 1;
    }   
}
numbering();
```

### 배열 - 선언시 대괄호를 사용한다.

```javascript
var coworkers = ['egoing','leezche','duru','taeho'];

// 마지막 인덱스로 부터 원소 1개 추가
coworkers.push('Lee');

// 마지막 인덱스로 부터 원소 복수 추가
coworkers.concat(['Kim', 'Park']);

// 첫 번째 인덱스에 추가
coworkers.unshift('Jeong');

// 특정 인덱스 제거 및 추가
coworkers.splice(2) // coworkers는 2인덱스부터 ~ 끝까지 삭제된다
coworkers.splice(2, 4) // coworkers는 2인덱스부터 4인덱스까지 삭제된다
coworkers.splice(2, 4, 'cow', 'hourse) // coworkers는 2인덱스부터 4인덱스까지 삭제되고 2인덱스부터 'cow', 'hourse'가 추가된다
coworkers.splice(2, 0, 'dog', 'cat') // coworkers는 2인덱스부터 'dog', 'cat'이 추가된다

// 첫 번째 원소 제거
coworkers.shift();

// 마지막 원소 제거
coworkers.pop();

// 정렬
coworkers.sort();
coworkers.reverse();
```

script 태그를 사용해 사용한다.

* document.write() 
* document.querySelectorAll()



document.querySelector('body').style.color = 'black'; -> css의 selector로 타겟 지정 
  
### JavaScrpit 비교연산자 ===

### this 연산자 - 태그를 가리킨다. / 함수로 빼서 사용한다면 매개변수로 전달 해야한다.


```

### Object  / 컴마로 구분
```javascript
var student = {
  "1" : "kim",
  "2" : "Lee"
};

for(var key in student){
  document.write(key + " : " + student[key]);}
```


#### CSS 특징 ':' , ';', '{}'
'.' -> class; '#' -> id; 그냥쓰면 태그

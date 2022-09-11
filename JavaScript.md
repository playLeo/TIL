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

### 객체 - key, value

선언방법 3가지

1. 
```javascript
var student = {"1" : "kim",  "2" : "Lee"};
```
2. 
```javascript
var student = {};
student["1"] = "kim";
student["2"] = "Lee";
```
3. 
```javascript
var student = new Object();
student["1"] = "kim";
student["2"] = "Lee";
```

#### value 접근 방법
* key를 "1" 로해도 숫자로 저장된다. student["1"]
* key가 문자라면 stduent.key로 접근 가능하다


#### 객체에는 객체 or 함수도 담을 수 있다.
```javascript
var ages = {
    'list': {'aa': 20, 'bb': 23, 'cc': 32},
    'show' : function(){
        for(var age in this.list){
            document.write(age+':'+this.list[age]+"<br />");
        }
    }
};
ages.show();
```

### 모듈
순수한 자바스크립트에서는 모듈(module)이라는 개념이 분명하게 존재하지 않는다. 하지만 자바스크립트가 구동되는 호스트 환경에 따라서 서로 다른 모듈화 방법이 제공되고 있다.

<details>
<summary>호스트 환경이란?</summary>
</br>
 호스트 환경이란 자바스크립트가 구동되는 환경을 의미한다. 자바스크립트는 부라우저를 위한 언어로 시작했지만, 더 이상 브라우저만을 위한 언어가 아니다. </br></br>
 예를들어 node.js는 서버 측에서 실행되는 자바스크립트다. 이 언어는 자바스크립트 문법을 따르지만 이 언어가 구동되는 환경은 브라우저가 아니라 서버측 환경이다. 또 구글의 제품 위에서 돌아가는 Google Apps Script 역시 자바스크립트지만 google apps script가 동작하는 환경은 구글 스프레드시트와 같은 구글의 제품 위이다. </br></br>
 이처럼 PHP와 같은 서버 시스템을 제어하는 node.js, 구글 스프레드시트, 웹 브라우저가 자바스크립트가 동작하는 호스트 환경이다.
 
</details>

1. 웹 브라우저에서의 모듈화
```javascript
// js파일 ~~.js생성 후
<script src="~~.js"></script>
```
2. node.js에서의 모듈화

node.circle.js(로드될 대상)
```javascript
var PI = Math.PI;
  
exports.area = function (r) {
return PI * r * r; };
  
exports.circumference = function (r) {
return 2 * PI * r;} ;
```

node.demo.js(로드의 주체)
```javascript
var circle = require('./node.circle.js');
console.log( 'The area of a circle of radius 4 is '
           + circle.area(4));
```

### 정규표현식 - 문자열에서 특정한 문자를 찾아내는 도구

정규표현식은 두가지 단계로 이루어진다. 하나는 컴파일 다른 하나는 실행이다.

1. 컴파일 - 검출하고자 하는 패턴을 만드는 일

```javascript
// 정규표현식 리터럴
var pattern = /a/;
// /a/i -> 대소문자 구분 X
// /a/g -> 검색된 모든결과 리턴

// 정규표현식 객체 생성자
var pattern = new RegExp('a');

// 2. 실행
// exec(), test()
pattern.exec('abcd'); //['a']
pattern.exec('bcde'); // null
pattern.test('abcd') // true
pattern.test('bcde') // false

'abcdef'.match(pattern); // ['a']
'bcdefg'.match(pattern); // null

'abcdef'.replace(pattern, 'A'); // ['Abcdef']
```

#### 캡쳐
```javascript
var pattern = /(\w+)\s(\w+)/;
var str = "coding everybody";
var result = str.replace(pattern, "$2, $1"); // 괄호안의 패턴은 변수처럼 사용할 수 있다.
console.log(result); // everybody, coding
```


script 태그를 사용해 사용한다.

* document.write() 
* document.querySelectorAll()


### 유효범위
```javascript
var vscope = 'global';
function fscope(){
    vscope = 'local';
    alert('함수안'+vscope); // 함수안local
}
fscope();
alert('함수밖'+vscope); // 함수밖local
```

fscope 함수 내에서 vscope를 정의 할 때, var를 사용하지 않고 정의 했기 때문에 지역변수 생성이 아닌 전역변수 값 교체가 된 것이다.

변수 선언시 var를 붙여 지역변수로 사용해야 좋다. 전역변수는 누군가에 의해 변경될 수 있고, 다른 어플리케이션 이식에 문제가 발생 할 수 있다. 

* 불가피하게 전역변수를 사용해야 하는 경우는 하나의 객체를 전역변수로 만들고 객체의 속성으로 변수를 관리한다.
```javascript
MYAPP = {}
MYAPP.calculator = {
    'left' : null,
    'right' : null
}
MYAPP.coordinate = {
    'left' : null,
    'right' : null
}
 
MYAPP.calculator.left = 10;
MYAPP.calculator.right = 20;
function sum(){
    return MYAPP.calculator.left + MYAPP.calculator.right;
}
document.write(sum());
```

* 전역변수를 사용하고 싶지 않다면 아래와 같이 익명함수를 호출해 모듈화 한다.

document.querySelector('body').style.color = 'black'; -> css의 selector로 타겟 지정 

```javascript
(function(){
    var MYAPP = {}
    MYAPP.calculator = {
        'left' : null,
        'right' : null
    }
    MYAPP.coordinate = {
        'left' : null,
        'right' : null
    }
    MYAPP.calculator.left = 10;
    MYAPP.calculator.right = 20;
    function sum(){
        return MYAPP.calculator.left + MYAPP.calculator.right;
    }
    document.write(sum());
}())
```

**자바스크립트는 함수에 대한 유효범위만을 제공한다!!**
```javascript
for(var i = 0; i < 1; i++){
    var name = 'coding everybody';
}
alert(name); // coding everybody 정상 출력
```

**자바스크립트는 함수가 선언된 시점에서 유효범위를 갖는다. 정적 유효범위**
```javascript
var i = 5;
 
function a(){
    var i = 10;
    b(); // 함수내 사용되는 시점인 지역변수 i를 쓰는게 아니라 b함수가 정의될 때, 전역변수 i를 사용한다.
}
 
function b(){
    document.write(i);
}
 
a();  // 5
```

### this 연산자 - 태그를 가리킨다. / 함수로 빼서 사용한다면 매개변수로 전달 해야한다.






for(var key in student){
  document.write(key + " : " + student[key]);}



#### CSS 특징 ':' , ';', '{}'
'.' -> class; '#' -> id; 그냥쓰면 태그

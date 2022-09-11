## JavaScript란 ?

JavaScript는 웹페이지에서 복잡한 기능을 구현할 수 있도록 하는 스크립팅 언어이다.

DOM API를 통해 HTML과 CSS를 동적으로 수정, 사용자 인터페이스를 업데이트하는 일에 가장 많이 사용된다.

주기적으로 갱신되거나, 사용자와 상호작용이 가능하거나, 애니메이션이 적용된 2D/3D 그래픽을 볼 수 있다면 JavaScript가 관여하고 있을거라고 생각해도 좋다.

script 태그를 사용해 사용한다.

* document.write() 
* document.querySelectorAll()

### CSS 특징 ':' , ';', '{}'
'.' -> class; '#' -> id; 그냥쓰면 태그

document.querySelector('body').style.color = 'black'; -> css의 selector로 타겟 지정 
  
### JavaScrpit 비교연산자 ===

### this 연산자 - 태그를 가리킨다. / 함수로 빼서 사용한다면 매개변수로 전달 해야한다.

### 배열 선언에서 값을 할당할 때도, 대괄호를 쓴다.
```javascript
var coworkers = ['egoing','leezche','duru','taeho'];
document.write('<li><a href="http://a.com/'+coworkers[i]+'">'+coworkers[i]+'</a></li>');
```
### Function
```javascript
fuction backgroundSetColor(color){
  document.querySelector('body').style.backgroundColor = color;
}
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
  

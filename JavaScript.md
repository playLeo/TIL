script 태그

document.write() - 동적으로 작동 html 태크 안에 event

document.querySelectorAll()

### SS 특징 ':' , ';', '{}'
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
  

## 목차
1. [Vue.js란?](#vuejs란)
2. [문법 - 바인딩](#데이터-표시)
3. [문법 - 데이터 타입](#데이터-종류)
4. [문법 - 디렉티브](#디렉티브)

<details>
<summary>Spring & Vue.js 연동</summary>
참고자료 - https://agal.tistory.com/180
 
기본적으로 java, npm, vue 가 설치되어 있어야 한다.

node.js 설치후 npm으로 vue cli설치

<details>
<summary>npm 이란?</summary>
<div mardown="1">
  
  
npm은 Node Packaged Manager의 약자다.
  
Node.js로 만들어진 모듈을 웹에서 받아서 설치하고 관리해주는 프로그램이다.
개발자는 단 몇 줄의 명령어로 기존에 공개된 모듈들을 설치하고 사용할 수 있다.
  
* npm init : npm을 쓸 수 있는 초기 환경을 설정해 준다.
  
* npm install '패키지명' : 원하는 패키지를 설치할 수 있다.
  * npm install -D '패키지명' : 개발할때는 이 패키지를 사용해서 개발하겠지만, 서버가 개발된 이후에는 이 패키지가 사용되지 않을때 -D를 사용한다.
  * npm install -g '패키지명' : 패키지를 모두 공통적으로 설치하겠다는 의미이다.
  
참고 - https://velog.io/@yoojinpark/npm


</div>
</details>


intellij 프로젝트 안에서 npm 명령어를 powerShell, Command Prompt로 해봐도 안되서 cmd 창으로 설치하고 node -v 와
npm -v로 설치를 확인했다.

* npm install -g @vue/cli

프로젝트에서 Prompt 창에서

* vue create vue-frontend 실행후 V2 버전으로 설치했다.

프로젝트 바로 하위에 vue-frontend 패키지가 성성된다.

vue.config.js파일 수정
```js
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  outputDir: "../src/main/resources/static",
  indexPath: "../static/index.html",
  devServer: {
    proxy: "http://localhost:8080"
  },
  chainWebpack: config => {
    const svgRule = config.module.rule("svg");
    svgRule.uses.clear();
    svgRule.use("vue-svg-loader").loader("vue-svg-loader");
  }
};

```

Prompt에서 vue-frontend로 이동후 npm run build 실행하면 src.main.resources.static 에 js 패키지가 생성된다.


localhost:8080으로 접속하면 프로젝트에 build된 파일들이 나타나는 거라 실시간으로 수정되는 소스를 매번 빌드, 확인하면서 개발할 수가 없기에 명령어 프롬프트로

### 프로젝트 동작 원리
* Vue 프로젝트 개발 후 Build -> SpringBoot(static 폴더에 생성)
* SpringBoot 실행
* 웹 페이지 접속(경로지정) -> SpringBoot 프로젝트 내의 static 폴데어 생긴 Vue 결과물 실행

 
</details>

## Vue.js란?

컴포넌트 기반의 SPA를 구축할 수 있게 해주는 프레임워크

MVVC 패턴의 ViewModel에 해당하여, UI코드와 데이터제어 로직을 분리

컴포넌트(Component)
* 웹을 구성하는 로고, 메뉴바, 버튼, 모달창 등 웹 페이지 내의 다양한 UI 요소
* 재사용 가능하도록 구조화 한 것.

SPA(Single Page Application)
* 단일 페이지 어플리케이션
* 하나의 페이지 안에서 필요한 영역 부분만 로딩되는 형태(라우팅)
* 빠른 페이지 변환
* 적은 트패픽 양

Q. SPA를 구축할 수 있게 해주는걸 어떻게 해주지? AJAX를 이용하는 건가?


![MVVM 패턴](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcVeP4L%2FbtrvZIubnnT%2FCZKbN7k7uHkvIssYIAgjWk%2Fimg.png)

* View(html DOM) - 사용자에게 보이는 화면
* Model(JS) - 데이터를 담는 용기, 보통 서버에서 가져온 데이터를 javascript 객체로 저장
* ViewModel - View와 Model의 중간 영역으로 DomListener와 DataBinding을 제공한다.

* DOM - HTML 문서에 들어가는 요소의 정보를 담고 있는 데이터 트리
* DOM Listener - DOM의 변경에 대한 즉각적으로 반응하여 특정 로직을 수행하는 장치
* Data Binding - View에 표시되는 내용과 모델의 데이터를 동기화
  * vue에서는 기본적으로 단방향 데이터바인딩으로 컴포넌트간 통신은 상위 컴포넌트->하위 컴포넌트로 전달   

### Vue.js 장점

* 배우기 쉽다
* React, Angular에 비해 가볍고 성능이 빠르다.
* React(Virtual DOM), Angular(데이터 바인딩)의 장점을 취했다.
* 컴포넌트 기반 프레임워크로 레고 블록과 같이 컴포넌트 조합으로 뷰를 구성하고, 재사용하기 쉽다.

> Virtual DOM
>
> 화면에 변화가 있을 때 마다 실시간으로 DOM Tree를 수정하지 않고 변경사항이 반영된 Virtual DOM을 이용해 메모리에서 처리하고 한 번만 DOM 수정을 통해 브라우저의 렌더링을 한번만 처리하게되어 성능을 높인다.



프론트 프레임워크(vue.js, react.js)를 사용할 때에는 백엔드 구성을 Node.js를 사용한다.
하지만, SpringBoot로 백엔드를 구성하여 사용해보자.





___

### Vue.js 디렉토리 구조
1. main.js - vue 인스턴스를 생성하고 그안에 router, store 그리고 랜더링 할 vue를 설정한다. 새로운 라이브러리를 설정할 때 해당 파일에 연겨래서 사용한다.
2. App.vue & router/index.js 파일
3. views/Home.vue & views/About.vue 파일 - views 폴더는 사용자가 보이는 뷰들을 관리하는 폴더
4. asset - public 한 이미지나 파일들을 저장

**Vue가 실행되지 않아 아래 작업을 수행했다.**

powerShell 관리자 권한으로 실행

get-executionpolicy // 확인

set-executionpolicy remotesigned // 체크

get-executionpolicy // 확인


# 문법

## Vue 인스턴스
Vue.js로 프론트엔드를 개발할때는 Vue 인스턴스를 만드는 것부터 시작한다.
```js
new Vue({
    el: "#app",
    data: {
        text: 'hello world'
    },
})
```

## 데이터 표시 
mustache - {{데이터}} 

```js
<!DOCTYPE html>
<html>
  ...
  <body>
    <div id="app">
      <p> {{ myText }}</p>  <!--myText의 값 출력-->
    </div>

    <script>
      new Vue({
        el: '#app',          //id="app"
        data: {              
          myText:'Hello!!!'  //myText라는 프로퍼티
        }
      })
    </script>
  </body>
</html>
```

## 데이터 종류
Vue는 숫자형, 문자형, Boolean 타입이 있다.

```js
 <body>
    <div id="app">
      <p>{{ myPrice * 1.08 }}</p>
      <p>{{ "안녕하세요~ "+ myName + "님" }}</p>
      <p>{{ myName.substr(0,1) }}</p>
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          myPrice:500,       //숫자형
          myName:'홍길동'    //문자형
        }
      })
    </script>
  </body>
```

배열과 객체도 data 객체에 넣어 사용할 수 있다.
```js
<body>
    <h2>배열로 값을 표시하는 예제</h2>
    <div id="app">
      <p>{{ myArray }}</p>
      <p>{{ myArray[0] }}</p>
      <p>{{ myObject.name }}</p>
      <p>{{ myObject.price }}</p>
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          myArray:['다즐링','얼그레이','실론'],
          myObject:{name:'다즐링', price:600}
        }
      })
    </script>
</body>
```

### 디렉티브
디렉티브는 Vue에서 HTML 요소에 대해 실행하는 명령어이다. 디렉티브는 'v-'가 붙어있다.

* v-test
* v-html

```js
<body>
    <h2>HTML로 표시하는 예제</h2>
    <div id="app">
        <p>{{ myText }}</p> // <h1>Hello!!!</h1>
        <p v-text="myText"></p> // <h1>Hello!!!</h1>
        <p v-html="myText"></p> Hello!!! -> h1태그가 적용된 Hello!!!가 나온다.
    </div>
    <script>
        new Vue({
            el: '#app',
            data: {
                myText:'<h1>Hello!!!</h1>'
            }
        })
    </script>
</body>
```

* v-bind : 태그의 속성을 vue에서 지정할 수 있는 디렉티브

```js
// 라디오와 url이 작동하지 않는다. 따로하면 되는데 이유는 모르겠다.
<body>
    <div id="app">
      <input :type='type1'>        <!-- v-bind 생략 가능 -->
      <input v-bind:type='type2'>
      <a :href='url'>url</a>
      <p v-bind:class='class1'>v-bind 클래스지정</p>
      <p v-bind:class='[class1, class2]'>다중 클래스 지정</p>
      <p v-bind:class="{'test-class1': isON}">클래스 ON/OFF</p>  <!-- isON의 값이 true/false-->
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          url:'https://naver.com',
          type1:'radio',
          type2:'text'
          class1:'test-class1',
          class2:'test-class2',
          isON: true
        }
      })
    </script>
</body>
```

* v-model : input에서 입력한 값을 프로퍼티에 저장
```js
<body>
    <h2>입력한 문자열을 표시하는 예제</h2>
    <div id="app">
      <!--v-model을 사용해 프로퍼티에 값을 input값으로 저장가능-->
      <input v-model="input_model" placeholder="이름">
      <p>input model : {{input_model}}</p>

      <!--lazy는 input의 포커스를 다른곳으로 이동할 때 프로퍼티에 저장하는 기능-->
      <input v-model.lazy="input_model2">
      <p>input lazy model : {{input_model2}}</p>

      <!--textarea에서도 v-model 사용가능-->
      <textarea v-model="textarea_model"></textarea>
      <p>textarea model : {{textarea_model}}</p>

      <!--checkbox에서 사용 예제 저장된 프로퍼티를 v-bind를 통해 로직 실행가능-->
      <input type="checkbox" v-model="checkbox_model" value="awd">
      <button v-bind:disabled="checkbox_model === false">disabledd</button>
      <p>checkbox model : {{checkbox_model}}</p>

      <!--프로퍼티를 배열로 선언하면 value값을 저장-->
      <input type="checkbox" v-model="checkbox_model2" value="test1">
      <input type="checkbox" v-model="checkbox_model2" value="test2">
      <p>checkbox model : {{checkbox_model2}}</p>

      <!--라디오 프로퍼티 저장-->
      <input type="radio" v-model="radio_model" value="test1">
      <input type="radio" v-model="radio_model" value="test2">
      <input type="radio" v-model="radio_model" value="test3">
      <p>radio model : {{radio_model}}</p>

      <!--select 프로퍼티 저장-->
      <select v-model="select_model">
        <option>test1</option>
        <option>test2</option>
      </select>
      <p>select model : {{select_model}}</p>
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          input_model:'기본값',
          input_model2:'',
          textarea_model:'',
          checkbox_model:false,
          checkbox_model2:[],
          radio_model:'',
          select_model:''
        }
      })
    </script>
</body>
```

* v-on : 이벤트 메소드와 연결

```js
<body>
    <div id="app">
      <!--v-on:click으로 클릭이벤트 설정 가능-->
      <button v-on:click="countUp">1씩증가</button>
      <p>{{ count }}</p>

      <button :disabled="click" v-on:click="oneClick">한번만 클릭 가능</button>
      <p>{{ text }}</p>

      <!--함수 param전달-->
      <button v-on:click="paramClick(10)">10씩증가</button>
      <p>{{ tenCount }}</p>

      <!--특정 키를 누를 때 함수 실행(포커스 상태에서)-->
      <input v-on:keyup.enter="alertClick">
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          count:0,
          click:false,
          text:"",
          tenCount:0
        },
        methods: {
          countUp() {
            this.count++;
          },
          oneClick() {
            this.click = true;
            this.text = "click";
          },
          paramClick(num) {
            this.tenCount += 10;
          },
          alertClick() {
            alert("enter");
          }
        }
      })
    </script>
  </body>
```
* v-if, v-else-if, v-else : 조건

```js
<body>
    <div id="app">
      <!--버튼 클릭시 클릭한 text출력과 클릭한 버튼 비활성-->
      <button v-bind:disabled="one" v-on:click="oneCheck">one</button>
      <button v-bind:disabled="two" v-on:click="twoCheck">two</button>
      <button v-bind:disabled="three" v-on:click="threeCheck">three</button>
      <p v-if="one">onc click</p>
      <p v-else-if="two">two click</p>
      <p v-else>three click</p>
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          one: false,
          two: false,
          three: true,
        },
        methods: {
          oneCheck() {
            this.one = true;
            this.two = false;
            this.three = false;
          },
          twoCheck() {
            this.one = false;
            this.two = true;
            this.three = false;
          },
          threeCheck() {
            this.one = false;
            this.two = false;
            this.three = true;
          }
        }
      })
    </script>
</body>
```

* v-for : 반복

```js
 <body>
    <div id="app">
      <ul>
        <!--arr배열의 각 요소를 item에 담는다-->
        <li v-for="item in arr">{{ item }}</li><br>

        <!--object의 key값과 value를 불러와 출력가능-->
        <li v-for="(value, key) in obj">{{ key }} : {{ value }}</li><br>

        <!--objectArray의 item(Object)를 출력-->
        <li v-for="(item) in objArr">{{ item.a}} : {{ item.b }} : {{ item.c }}</li><br>

        <!--입력값을 추가 하는 기능-->
        <input v-model="num">
        <button v-on:click="add(num)">add</button>
        <button v-on:click="deletes()">delete</button>
        <li v-for="item in pushArr">{{item}}</li>
      </ul>
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          arr: ['쨈빵','멜론빵','크로와사'],
          obj: {'a':1, 'b':2, 'c':3},
          objArr: [{'a':1, 'b':2, 'c':3}, {'a':4, 'b':5, 'c':6}],
          pushArr: [],
          num:0
        },
        methods: {
          add(val) {
            this.pushArr.push(val);
          },
          deletes() {
            this.pushArr.pop();
          }
        }
      })
    </script>
  </body>
```

## 데이터 변화 감지

### Computed

어떤 값을 출력할 때 따로 연산을 추가해서 넣고 싶을 때, computed 속성의 프로퍼티에 정의하여 사용한다.

```js
<body>
    <div id="app">
      <input v-model="price" type="number">원 x
      <input v-model="count" type="number">개
      <p>　　　합계 {{ sum }} 원</p>
      <p>세금포함 {{ taxIncluded }} 원</p>
    </div>
    <script>
      new Vue({
        el: '#app',
        data: {
          price: 100,
          count: 1
        },
        <!--computed의 함수들은 항상 값을 반환해야하고, 함수 내부의 프로퍼티가 변경되면 실행된다.-->
        computed: {
          sum: function () {
            return this.price * this.count;
          },
          taxIncluded: function() {
            return this.sum * 1.08;
          }
        }
      })
    </script>
</body>
```

### watch
watch도 기본적으로는 computed처럼 값이 변경되면 실행된다. 하지만 computed는 무조건 반환값이 있어야하고, watch는 그렇지 않다. computed는 계산식, watch는 어떤 로직에 따른 처리르 할때 사용한다.

```js
<body>
    <div id="app">
      <p>금지문자는、「{{ forbiddenText }}」</p>
      <textarea  v-model="inputText"></textarea>
    </div>

    <script>
      new Vue({
        el: '#app',
        data: {
          forbiddenText: '안되',
          inputText: '오늘은 날씨가 좋습니다.'
        },
        watch: {
          // 입력한 문자열을 감시한다.
          inputText: function(){
            //inputText프로퍼티가 계속변하기 때문에 watch에 살피다가 안되라는 단어가 나오면 삭제
            var pos = this.inputText.indexOf(this.forbiddenText);
            if (pos >= 0) {
              alert(this.forbiddenText + "는 입력할 수 없습니다.");
              this.inputText = this.inputText.substr(0,pos);
            }
          }
        }
      })
    </script>
  </body>
```

## Component
컴포넌트는 같은 종류의 처리를 컴포넌트로 묶어 여러곳에서 사용하게 하는 방법이다.

Component를 등록하는 방법은 글로벌과 로컬 두가지가 있다.

글로벌
```js
Vue.component('컴포넌트 태그', {template : "HTML 코드"});
```

글로벌은 위와같이 Vue.component를 사용해 등록할 수 있다. 이렇게 하면 이후에 생성되는 vue 인스턴스에서도 사용가능해진다. 전역변수라 생각하면 된다. 하지만 글로벌은 사용하지 않아도 계속 남아있기 때문에 성능에 문제가 생길 수 있다.


로컬
```js
<body>
    <div id="app">
      <!--컴포넌트 태그명을 사용해 컴포넌트 사용-->
      <my-component></my-component>
      <my-component></my-component>
      <my-component></my-component>
    </div>

    <script>
      //Component 변수
      var MyComponent = {
        template: `<p class="my-comp">Hello</p>`
      }
      new Vue({
        el: '#app',
        components: {     //컴포넌트 등록, 이후 컴포넌트태그를 HTML코드에 넣고 사용
          'my-component': MyComponent
        }
      })
    </script>
<body>
```

### props
컴포넌트를 사용할 때 HTML의 컴포넌트 태그에서 데이터를 props를 이용해 전달받을 수 있다.

주의할 점은 props의 프로퍼티명은 myName과 같이 카멜케이스로 쓰고 HTML에서는 my-name처럼 케밥케이스로쓴다.
```js
 <body>
    <h2>컴포넌트에 값을 전달하는 예제</h2>
    <div id="app">
      <!--my-name태그로 myName 프로퍼티에 값을 전달한다.-->
      <my-component my-name="철수"></my-component>
      <my-component my-name="영희"></my-component>
      <my-component></my-component>
    </div>

    <script>
      var MyComponent = {
        template: '<p class="my-comp">나는 {{ myName }} 입니다.</p>',
        props: {
          myName: String	//my-name으로부터 값을 받는다.
        },
      }
      new Vue({
        el: '#app',
        components: {
          'my-component': MyComponent
        }
      })
    </script>
  </body>
```

참고자료 https://cjw-awdsd.tistory.com/33

## Vue.js란?

컴포넌트 기반의 SPA를 구축할 수 있게 해주는 프레임워크

MVVC 패턴의 ViewModel에 해당하여, UI코드와 데이터제어 로직을 분리

컴포넌트(Component)
* 웹을 구성하는 로고, 메뉴바, 버튼, 모달창 등 웹 페이지 내의 다양한 UI 요소
* 재사용 가능하도록 구조화 한 것.

SPA(Single Page Application)
* 단일 페이지 어플리케이션
* 하나의 페이지 안에서 필요한 영역 부분만 로딩되는 형태
* 빠른 페이지 변환
* 적은 트패픽 양

Q. SPA AJAX와 같은 기술이 적용된건가? AJAX는 필요 없는건가 ???


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




## Spring & Vue.js 연동

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

Prompt에서 vue-frontend로 이동후
npm run build 실행하면 src.main.resources.static 에 js 패키지가 생성된다.



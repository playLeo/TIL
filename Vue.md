Vue.js란?

컴포넌트 기반의 SPA를 구축할 수 있게 해주는 프레임워크

컴포넌트(Component)
* 웹을 구성하는 로고, 메뉴바, 버튼, 모달창 등 웹 페이지 내의 다양한 UI 요소
* 재사용 가능하도록 구조화 한 것.

SPA(Single Page Application)
* 단일 페이지 어플리케이션
* 하나의 페이지 안에서 필요한 영역 부분만 로딩되는 형태
* 빠른 페이지 변환
* 적은 트패픽 양


## Spring & Vue.js 연동

node.js 설치후 npm으로 vue cli설치


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



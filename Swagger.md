api를 테스트 하기위해 Postman을 설치해야 하는 불편함이 있다 -> Swagger 문서 자동화 툴은 설정만으로 테스트 가능한 Web UI를 지원하기 때문에 서드파티 프로그램 설치 피요 없다.

또한 최소한의 작업을 통해 자동으로 API Document를 만들어주기 때문에 클라이언트 개발자에게 문서 내용을 전달하기 위해 추가 작업을 하지 않아도 된다.

## build.gradle에 swagger 라이브러리 추가

```java
implementation 'io.springfox:springfox-swagger2:2.9.2'
implementation 'io.springfox:springfox-swagger-ui:2.9.2'
```

* 22.09.04 기준 version 3.0.3까지 나왔지만 사용률이 높은 2.9.2 버전 사용

## config 패키지에 SwaggerConfig or SwaggerConfiguration파일 생성

```java
@Configuration
@EnableSwagger2

public class SwaggerConfig {
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(swaggerInfo()).select()
                //hello.itemservice.controller하단의 Controller 내욜을 읽어 mapping 된 resource들을 문서화 시킨다.
                .apis(RequestHandlerSelectors.basePackage("hello.itemservice.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false); // 기본으로 세팅되는 200,401,403,404 메시지를 표시 하지 않음
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation")
                .description("웹 개발시 사용되는 서버 API에 대한 연동 문서입니다")
                .license("Leo").licenseUrl("").version("1").build();
    }
}
```

## Controller 수정



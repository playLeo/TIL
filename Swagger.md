## Swagger란?

Swagger란 Open Api Specification(OSA)를 위한 프레임워크다.
즉, 간단히 말해 API 스펙을 명세, 관리할 수 있는 프로젝트/문서 라고 정의할 수 있다.

Swagger는 Java나 Spring에 기본 종속된 라이브러리가 아니다.
이 말은 다시 말해 Dependecy를 직접 주입해야 하며, 버전을 자동 관리해주는 SpringBoot라 할지라도 Swagger의 버전은 직접 명시해줘야 한다.

Swagger 문서 자동화 툴은 설정만으로 테스트 가능한 Web UI를 지원하기 때문에 서드파티 프로그램 설치 필요 없다.

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
                //hello.itemservice.controller하단의 Controller 내용을 읽어 mapping 된 resource들을 문서화 시킨다.
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

@EnableSagger2 - Swagger를 설정할 수 있게 활성화 시켜주는 어노테이션

Docket 빈을 정의 후, select() 메소드는 ApiSelectorBuilder 인스턴스 리턴하는데 이는 스웨거에 의해 노출되는 끝단 endpoint을 제어하는 하나의 방법을 제공해준다.

RequestHandlers의 selection을 위한 서술부는 RequestHandlerSelectors와 Pathselectors의 도움을 받아 설정할 수 있다. 둘 다 any()를 쓰면 사용자의 전체 API가 스웨거를 통해 문서화 되어질 것이다.


## Controller 수정

@Api(tags = {"1.User"})
controller를 대표하는 최상단 타이틀 영역에 표시될 값을 세팅한다. (기본은 케밥표기법)

@ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다.")
각각의 resource에 제목과 설명을 표시하기 위해 세팅한다.

@ApiParam(value="회원 이름", required = true)@RequestParam ~~~
파라미터에 대한 설명을 보여주기 위해 세팅한다.

## Entity 수정


## 스프링 부트 없이 설정하기
스프링 부트를 사용하지 않는다면 사용자는 리소스 핸들러의 자동설정 혜택을 받을 수 없다. 스웨거 UI는 리소스의 세트를 추가하는 데 이를 사용자가 @EnableWebMvc어노테이션을 가지는, WebMvcConfigurerAdpter를 확장한 하나의 클래스와 일부로서 설정해주어야 한다.


!참조
https://springboot.tistory.com/tag/Swagger

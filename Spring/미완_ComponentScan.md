spring의 코드삽입을 공부하다 spring은 '프록시 객체 사용'을 사용하고, springboot는 '바이트 코드 생성'을 사용한다고 봤다.
-> 다이내믹 프록시 기술
-> CGLIB 기술

프록시 객체 사용과 바이트 코드 생성의 차이를 점을 알아보려던 중, 예전에 배웠던 @Configuration를 통한 수동빈 설정과 ApplicationContext를 통한 등록과 사용,
ComponentScan을 자동빈 등록을 하지않고 프로젝트를 만들고 있다는걸 깨달았다.

ac.getBean을 통해 빈을 출력해 CGLIB가 사용 되었는지 확인해 보려 했는데 ComponentScan을 한다면 어떤식으로 Bean을 꺼내오는지와, Appconfig
파일은 왜 안만들어도 되는지 몰라 찾아봤다.

1. config 파일을 통해 수동 빈 등록 and ComponentScan을 통한 자동 빈 등록 모두 ApplicationContext로 Bean을 가져올 수 있다.
2. @SpringBootApplication을 통한 config파일 없이 등록된 Bean은 빈을 직접 가져오는건 못찾았고, DI로 받은 객체를 .getClass로 출력해보니 CGLIB가 적용 됐다.


## @SpringBootApplication 에노테이션
> @Target(ElementType.TYPE)
> @Retention(RetentionPolicy.RUNTIME)
> @Documented
> @Inherited
> @SpringBootConfiguration
> @EnableAutoConfiguration
> @ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
> 		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
> public @interface SpringBootApplication {

SpringBootApplication은 크게 3가지로 구성되어 있다.
1. @SpringBootConfiguration - @Configuration과 같은 기능 수행
2. @EnableAutoConfiguration - classpath에 있는 '/resource/META-INF/spring.factories' 중 'EnableAutoConfiguration' 부분에 정의된 Configuration들을 자동 등록한다.
3. @ComponentScan - classpath 하위의 @Component 에노테이션을 스캔해 Bean으로 등록한다.

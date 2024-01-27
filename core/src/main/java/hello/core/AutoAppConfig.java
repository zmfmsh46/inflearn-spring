package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/*
    @Component 어노테이션이 붙은 클래스를 자동으로 스캔해서 스프링 컨테이너에 등록해줌.
    스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용.
    이름을 직접 지정하고 싶으면 @Component("name")으로 부여가능하다.
    @Component, @Controller, @Service, @Repository, @Configuration 어노테이션은 @Component을 포함하고 있어 이들을 대상으로 탐색한다.
    어노테이션은 상속관계가 없는데, 어노테이션이 특정 어노테이션을 들고 있는 것을 인식할 수 있는 것은
    자바 언어가 지원하는 것이 아닌, 스프링이 지원하는 기능이다.
 */
@ComponentScan(

        //해당 위치에서부터 컴포넌트를 탐색한다.
        basePackages = "hello.core.member",
        /*
            지정한 클래스의 패키지를 탐색 시작 위치(package hello.core)로 지정한다.
            지정하지 않을 시, 디폴트값으로 @ComponentScan을 붙인 클래스(class AutoAppConfig)의 패키지를 탐색 시작 위치로 지정한다.
            권장사항 : 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 둔다.
            결과적으로 스프링 부트에서는 @SpringBootApplication 안에 @ComponentScan이 포함되있기 때문에
            @SpringBootApplication이 붙은 파일을 최상단에 둔다.
         */
        basePackageClasses = AutoAppConfig.class,
        //@Configuration 어노테이션 타입을 제외하고 스캔
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /*
        자동 빈 등록과 자동 빈 등록으로 빈 이름이 충돌되면 오류를 발생시킨다.
        ConflictingBeanDefinitionException 예외 발생.

        수동 빈 등록과 자동 빈 등록으로 빈 이름이 충돌되면,
        수동 빈 등록이 우선권을 가져 수동 빈이 자동 빈을 오버라이딩 해버린다.
        현재는 이 경우도 충돌이 나면 오류가 발생하도록 기본 값을 바꾸었다.
        application.properties 에서 spring.main.allow-bean-definition-overriding=true 설정을 통해 허용가능.
     */
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}

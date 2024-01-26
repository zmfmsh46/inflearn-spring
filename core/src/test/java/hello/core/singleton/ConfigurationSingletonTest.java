package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }


    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());

        /*
        싱글톤을 보장해주는 @Configuration 어노테이션. (CGLIB사용함)

        순수한 클래스라면 class hello.core.AppConfig 까지만 출력되어야 하지만, xxxCGLIB가 붙은것을 확인할 수 있음.
        이는 내가 만든 클래스가 아닌 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은
        임의의 다른 클래스를 만들고, 그 클래스를 스프링 빈으로 등록한 것이다.
        그 클래스는 @Bean 이 붙은 메서드마다 이미 스프링 컨테이너에 스프링 빈이 존재하면 빈을 반환하고,
        없다면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다. 따라서 싱클톤이 보장되는 것이다.

        @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
         */
    }
}

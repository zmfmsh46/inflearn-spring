package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
//생성한 객체 인스턴스의 창조(레퍼런스)를 생성자를 통해서 주입(연결)해준다.
@Configuration
public class AppConfig {

    /*
    @Bean memberService -> new MemoryMemberRepository()
    @Bean orderService -> new MemoryMemberRepository()
     각각 다른 2개의 MemoryMemberRepository 가 생성되면서 싱글톤이 깨지는 것처럼 보임.

    예상되는 호출
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService
    call AppConfig.memberRepository
    call AppConfig.memberRepository

    실제 호출
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService


     */
    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        //return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {

        return new RateDiscountPolicy();
    }

}

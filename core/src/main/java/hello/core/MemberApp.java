package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //ApplicationContext 를 스프링 컨테이너라고 함. 스프링은 모든 것이 스프링 컨테이너(ApplicationContext)에서 시작함.
        //스프링 컨테이너는 AppConfig의 설정정보를 사용한다. 여기서 Bean으로 설정된 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너로 등록한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //스프링 컨테이너에서 @Bean으로 설정된 memberService 를 가져올 때 getBean(이름,타입)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member : " + member.getName());
        System.out.println("find member : " + findMember.getName());
    }
}

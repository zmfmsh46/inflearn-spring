package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final이 붙은 필드를 가지고 생성자를 자동으로 만들어줌.
public class OrderServiceImpl implements OrderService{

    //생성자 주입을 사용하면 필드에 final 키워드를 사용할 수 있으며, 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.
    //생성자 주입 방식 이외의 나머지 주입 방식은 생성자 이후에 호출되므로, final 키워드는 오직 생성자 주입 방식에서만 사용 가능.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //수정자(setter) 의존관계 주입
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 생성자가 한개일 때, @Autowired 생략가능.
    //@Autowired 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.
    //@Qualifier("mainDiscountPolicy") 로 주입할 때 해당 이름의 빈을 못찾으면, mainDiscountPolicy라는 이름의 스프링 빈을 추가로 찾는다.
    //최종적으로 못찾으면 NOSuchBeanDefinitionException 예외 발생.
    //@Qualifier가 @Primary 보다 우선권이 높다.
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

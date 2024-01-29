package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy // @Qualifier("mainDiscountPolicy") 의 경우 문자라서 잘못 적을 확률이 높아 따로 어노테이션을 만들어 쓸 수 있다.
//@Primary //@Autowired에서 타입이 같은 빈 2개이상 찾았을 때, RateDiscountPolicy를 우선적으로 찾도록 설정
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }

    }
}

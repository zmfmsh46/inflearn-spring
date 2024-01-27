package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    //확장은 열려잇으면서 변경은 닫히도록 하는 OCP 원칙을 위반
    //추상화를 의존하면서 구체화도 의존하고 있음을 보면 DIP 원칙을 위반하고 있음.
    private final MemberRepository memberRepository;

    //의존관계 주입 자동으로 해줌.
    //생성자를 생성하면서 스프링 컨테이너에 해당 타입과 같은 빈을 찾아 주입.
    @Autowired //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

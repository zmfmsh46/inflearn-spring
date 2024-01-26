package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //확장은 열려잇으면서 변경은 닫히도록 하는 OCP 원칙을 위반
    //추상화를 의존하면서 구체화도 의존하고 있음을 보면 DIP 원칙을 위반하고 있음.
    private final MemberRepository memberRepository;

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

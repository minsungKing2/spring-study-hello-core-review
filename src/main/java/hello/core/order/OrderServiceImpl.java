package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//주문 서비스 구현체(클라이언트)
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); -> 구현체에 의존. DIP 위반
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); -> 구현체에 의존. DIP 위반
//    private DiscountPolicy discountPolicy; //위 두 개의 코드 다 구현체에 의존하고 있다. DIP 위반 -> 인터페이스에만 의존하도록 변경.

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //의존관계 생성자 주입
    //@Autowired 생략 가능 //생성자가 딱 1개만 있으면 @Autowired 를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당한다.
    /*public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    /*
    //의존관계 수정자 주입(setter 주입)
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy
    }

    //의존관계 필드 주입(사용하지 않는 방식임.)
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;

    //의존관계 일반 메서드 주입(일반적으로 잘 사용하지 않음.)
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */
/*

    //생성자 자동 주입
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
*/

/*
    //수정자 자동 주입
    public DiscountPolicy setDiscountPolicy(@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

*/

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

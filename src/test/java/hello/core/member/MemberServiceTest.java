package hello.core.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

//회원 도메인 - 회원 가입 테스트
public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        assertThat(member).isEqualTo(findMember);
    }
}

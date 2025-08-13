package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

//컴포넌트 스캔과 의존관계 자동 주입
//컴포넌트 스캔을 사용하려면 먼저 @ComponentScan 을 설정 정보에 붙여주면 된다.
//기존의 AppConfig 와는 다르게 @Bean 으로 등록한 클래스가 하나도 없다!
//@ComponentScan 은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
@Configuration
@ComponentScan(
        basePackages = "hello.core", //basicPackages: 탐색할 패키지의 시작 위치를 지정, 이 패키지를 포함해서 하위 패키지 모두 탐색
        excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

    //수동 빈 등록과 자동 빈 등록에서 빈 이름이 중복되어 충돌되면, 수동 빈 등록이 우선권을 가져, 수동 빈이 자동 빈을 오버라이딩 해버린다.
    //-> 로그: Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing
   /* @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}

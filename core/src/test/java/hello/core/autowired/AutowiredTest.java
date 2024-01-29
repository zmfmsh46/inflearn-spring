package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        /*
            자동 주입 대상을 옵션으로 처리하는 방법 3가지
            1. @Autowired(required = false) : 자동 추입할 대상이 없으면 수장자 메소드 자체가 호출 안됨.
            2. @Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
            3. Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
         */

        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean1(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}

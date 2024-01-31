package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isNotSameAs(bean2);

        /*
            프로토타입 빈
            스프링 컨테이너에 요청할 때 마다 새로 생성된다.
            스프링 컨테이너에서 프로토타입 빈은 조회할 때 객체생성> 의존관계 주입> 초기화 까지만 관여한다.
            따라서 close()로 스프링 컨테이너를 종료해도 destroy()같은 종료 메서드가 호출되지 않는다.
            프로토타입 빈을 조회한 클라이언트가 관리해야하며, 종료 메서드에 대한 호출도 클라이언트가 직접 해야한다.
         */
        ac.close();
        //수동종료
        bean1.destroy();
        bean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init() {
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("prototypeBean.destroy");
        }
    }
}

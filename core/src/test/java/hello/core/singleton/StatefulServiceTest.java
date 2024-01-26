package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

/*
    싱글톤패턴이나, 스프링 같은 싱글톤 컨테이너처럼 객체 인스턴스를 하나만 생성해서
    공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기
    때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다. 무상태(=stateless)로 설계해야 함
 */
class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);
        //ThreadA : A사용자 주문 금액 조회
        //int price = statefulService1.getPrice();
        //System.out.println("price = " + price);

        //실제 A사용자가 주문한 금액은 10000원이지만, 하나의 객체를 공유하는 싱글톤 객체이기 때문에
        //그 다음에 B사용자가 주문한 금액 20000원이 저장되고나서 조회를 했기 때문에 싱글톤 객체의 price 변수값에는 20000이 저장되있음.
        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        System.out.println(userAPrice);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
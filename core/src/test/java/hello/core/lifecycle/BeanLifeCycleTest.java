package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
/*
    스프링 빈의 이벤트 라이프사이클
    스프링 컨테이너 생성 > 스프링 빈 생성 (객체 생성) > 의존관계 주입 > 초기화 콜백 > 사용 > 소멸전 콜백 > 스프링 종료
    스프링은 의존관계 주입 완료 시 스프링 빈에게 콜백 메서드를 통해 초기화 시점을 알려주는 기능 제공.
    또한 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 주기 때문에 안전하게 종료 작업 진행 가능.
 */
public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    static class LifeCycleConfig {

        //빈 등록 초기화, 소멸 메서드 사용
        //코드가 아니라 설정 정보를 사용하기 떄문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용가능.
        //@Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

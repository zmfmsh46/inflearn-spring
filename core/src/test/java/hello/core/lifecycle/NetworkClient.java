package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// implements InitializingBean, DisposableBean  : 빈 등록 초기화, 소멸 인터페이스
public class NetworkClient{

    private String url;

    //생성자 생성 후 의존관계 주입
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    //의존관계 주입이 끝나면 호출
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    //빈이 종료될 때 호출
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
    /*
    //의존관계 주입이 끝나면 호출
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    //빈이 종료될 때 호출
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
     */

}

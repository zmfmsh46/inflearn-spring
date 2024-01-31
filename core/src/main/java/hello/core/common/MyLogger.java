package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
/*
    @Scope(value = "request") : 요청이 들어오고 나갈때까지 라이프사이클을 가짐.
    proxyMode : MyLogger를 상속받은 가짜 프록시 객체를 만들어두고, HTTP request와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.
    적용 대상이 클래스면 TARGET_CLASS, 인터페이스라면 INTERFACES 선택.
    가짜 프록시 객체는 진짜 MyLogger를 찾는 방법을 알고 있다.
    가짜 프록시 객체는 실제 @Scope(value = "request") 와는 관계가 없고, 내부에 단순한 위임 로직만 존재하며, 싱글톤처럼 동작한다.
    Provider나 프록시를 사용하든지 간에 중요한 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다.
    어노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있다. > 이것이 다형성과 DI 컨테이너가 가진 큰 장점이다.
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}

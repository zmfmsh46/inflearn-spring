package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    //@Scope(value = "request")으로 설정을 하면 실제 고객의 요청이 와야 생성되므로,
    //처음 실행 후 생성자로 의존관계를 주입받을 시점에서는 MyLogger 스코프 빈은 아직 생성되지 않아 오류가 발생한다.
    //Provider를 사용하여 myLoggerObjectProvider.getObject(); 를 호출하는 시점까지 request scope 빈의 생성을 지연할 수 있다.
    private final MyLogger myLogger;
    //MyLogger를 찾을 수 있는(DL : Dependency Lookup) 이 주입된다.
    //주입 시점에 주입받을 수 있다.
    //private final ObjectProvider<MyLogger> myLoggerObjectProvider;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURI().toString();
        //myLoggerObjectProvider.getObject(); 시점에서는 HTTP요청이 진행중이므로 myLogger 스코프 빈 생성이 정상 처리된다.
        //MyLogger myLogger = myLoggerObjectProvider.getObject();
        //CGLIB 라이브러리로 클래스를 상속받은 가짜 프록시 객체를 만들어 대신 주입하고, 실제 호출하는 시점에 진짜 빈을 요청하는 위임 로직을 이용해 동작한다.
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        //myLoggerObjectProvider.getObject();을 여러 곳에서 따로따로 호출해도 같은 HTTP요청이면 같은 스프링 빈이 반환된다.
        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");

        return "OK";
    }
}

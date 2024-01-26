package hello.core.singleton;

public class StatefulService {

    //private int price; // 상태를 유지하는 필드
    //필드 대신 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.

//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price " + price);
//        this.price = price; // 여기가 문제!
//
//    }

    public int order(String name, int price) {
        System.out.println("name = " + name + " price " + price);
        return price;

    }

//    public int getPrice() {
//        return price;
//    }
}

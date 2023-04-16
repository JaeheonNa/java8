package com.study.java8;

public interface DefaultAndStatic {
    void printName();

    // 디폴트 메서드
    // 인터페이스를 구현한 모든 클래스에 자동으로 상속됨.
    // but, 아래 예시의 경우 getName()이 무슨 값을 리턴하는지 인터페이스는 모름.
    // 클래스 바디만 봐서 알 수도 없음.
    // 예를 들어 null이 들어올 수 도 있는 셈(RuntimeException 발생).
    // 따라서 문서화를 잘 해놔야.
    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    // 스태틱 메서드
    // 해당 인터페이스를 구현한 모든 인스턴스,
    // 또는 해당 타입에 관련돼 있는 유틸리티나 헬퍼 메서드를 제공하고 싶은 경우 상용.
    static void staticPrintName(){
        System.out.println("Jaeheon");
    }
    String getName();
}

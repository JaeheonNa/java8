package com.study.java8;


// 함수형 인터페이스와 람다 표현식 소개.
public class Class1_functionalInterface_lambda {
    public static void main(String[] args){
        // 함수형 인터페이스를 구현할 때 쓸 수 있는 람다 표현식.
        // Functional_noArgs이라는 인터페이스는 함수형 인터페이스로서 안에 구현해야할 추상 메소드(doIt())가 하나 존재.
        // functional_noArgs이라는 객체는 아래와 같이 추상 메서드가 정의돼 있음을 정의.
        Functional_noArgs functional_noArgs = () -> {
            System.out.println("Hello");
            System.out.println("My name is Jaeheon");
        };
        functional_noArgs.doIt();

        // 추상 메소드에서는 외부 변수를 참조하지 않는 것이 좋다.
        // 함수가 동일한 입력 값에 동일한 응답 값을 내지 않을 가능성이 존재하기 때문.
        Functional_intArgs functional_intArgs = (num) -> num+10;
        System.out.println(functional_intArgs.doIt(1));


    }
}

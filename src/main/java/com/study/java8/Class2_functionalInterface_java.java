package com.study.java8;

import java.util.function.Function;

// 자바에서 제공하는 함수형 인터페이스
public class Class2_functionalInterface_java {
    public static void main(String[] args){
        // apply라는 추상 메서드가 정의된 Function Interface.
        Function<Integer, Integer> plus10 = (num) -> num+10;
        Function<Integer, Integer> multiply2 = (num) -> num*2;
        System.out.println(plus10.apply(10));
        System.out.println(multiply2.apply(10));

        // compose : 디폴트 메소드. 함수 조합.
        // 값과 함수를 받아서 실행하고, 그 값을 받아서 실행.
        // ex) multiply -> plus10
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));

        // andThen : 디폴트 메소드. 함수 조합.
        // 값을 받아 실행하고, 실행 결과를 받은 함수에 다시 적용.
        // plus10 -> multiply
        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(multiply2);
        System.out.println(plus10AndMultiply2.apply(2));

        /*
        BiFunction<T, U, R>     : 입력 값을 두 개(T, U) 받아서 응답 값을 하나(R) 리턴하는 함수형 인터페이스.
        Consumer<T>             : 리턴 타입이 void인 함수형 인터페이스.
        Supplier<T>             : 입력 인자 없이 T 타입 값을 리턴.
        Predicate<T>            : 입력 인자를 받아서 boolean을 리턴.
        UnaryOperator<T>        : 입력 타입과 리턴 타입이 같은 경우 사용. Function을 상속 받음.
        BinaryOperator<T>       : 두 개의 입력 타입과 하나의 리턴 타입이 모두 같은 경우 사용.
         */
    }
}

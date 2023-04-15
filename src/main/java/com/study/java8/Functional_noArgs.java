package com.study.java8;

@FunctionalInterface
public interface Functional_noArgs {

    // 함수형 인터페이스: 추상 메서드가 하나만 있는 것.
    // abstract는 생략 가능.
    abstract void doIt();

    // 스태틱이나 디폴트 메소드는 몇 개가 더 있어도 상관 없음.
    static void printName(){
        System.out.println("Jaeheon");
    }

    default void printAge(){
        System.out.println(34);
    }
}

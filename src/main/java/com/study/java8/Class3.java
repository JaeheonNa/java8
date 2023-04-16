package com.study.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

// 메소드 레퍼런스
public class Class3 {
    public static void main(String[] args){
        // 일반적인 함수형 인터페이스 사용.
        Function<Integer, String> myAgeIs = (i) -> "My age is " + i;
        System.out.println(myAgeIs.apply(34));

        // 메소드 레퍼런스 사용.
        // :: <- 메소드 레퍼런스.
        // T::m <- T 클래스의 m 메소드를 함수로 쓰겠다는 뜻.
        // 클래스로 접근할 때 사용하려는 메소드는 static이어야 함.
        UnaryOperator<String> hi = Greeting::hi;
        System.out.println(hi.apply("Javis"));

        // 인스턴스로 접근하는 메소드는 static이 아니어도 됨.
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("Javis"));

        // 생성자 호출. Supplier이기 때문에 입력 인자 x. 따라서 디폴트 생성자 호출.
        Supplier<Greeting> getUnknownGreeting = Greeting::new;
        Greeting unknown = getUnknownGreeting.get();
        System.out.println(unknown.hello("there!"));

        // 생성자 호출. Function이기 때문에 입력 인자 존재. 따라서 입력 인자가 있는 생성자 호출
        Function<String, Greeting> getNamedGreeting = Greeting::new;
        Greeting ironMan = getNamedGreeting.apply("IronMan");
        System.out.println(ironMan.hello("there!"));

        String[] names = {"Jaeheon", "Loo", "Kyoungjin"};
        Arrays.sort(names, String::compareToIgnoreCase); // compareToIgnoreCase는 "Jaeheon", "Loo",,, 가 각각 가진 메소드임. static x.
        for (String name : names) {
            System.out.println(name);
        }
    }
}

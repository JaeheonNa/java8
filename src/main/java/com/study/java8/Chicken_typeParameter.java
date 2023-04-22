package com.study.java8;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_PARAMETER)
@Repeatable(ChickenContainer_typeParameter.class)
public @interface Chicken_typeParameter {
    String value();

    /*
    @Target에 넣어줄 인자 두 가지가 추가됨. ElementType.TYPE_PARAMETER, ElementType.TYPE_USE
    ElementType.TYPE_PARAMETER
        제네릭 클래스의 제네릭 타입에 붙일 수 있는 어노테이션이라는 뜻.
        ex)
        static class FeelsLikeChicken<@Chicken_typeParameter T>{
            public static <@Chicken_typeParameter C> void chicken(C c){
                System.out.println(c);
            }
        }
     */
}

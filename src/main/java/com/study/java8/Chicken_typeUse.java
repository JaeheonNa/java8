package com.study.java8;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(ChickenContainer_typeUse.class)
public @interface Chicken_typeUse {

    String value();
    /*
    @Target에 넣어줄 인자 두 가지가 추가됨. ElementType.TYPE_PARAMETER, ElementType.TYPE_USE
    ElementType.TYPE_USE
        제네릭 클래스의 제네릭 타입 뿐 아니라
        타입을 선언하는 모든 곳에 붙일 수 있는 어노테이션이라는 뜻.

        ex-1)
        static class FeelsLikeChicken<@Chicken_typeUse T>{
            public static <@Chicken_typeUse C> void chicken(@Chicken_typeUse C c){
                System.out.println(c);
            }
        }

        ex-2)
        List<@Chicken_typeUse String> names = new ArrayList<>();

        ex-3)
        public class main() throws @Chicken_typeUse RuntimeException {}
     */
}

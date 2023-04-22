package com.study.java8;

import java.util.Arrays;

/* 어노테이션의 변화
    어노테이션을 타입 선언부에도 사용할 수 있게 됨.
    어노테이션을 중복해서 사용할 수 있게 됨.
 */
@Chicken_typeUse("프라이드")
@Chicken_typeUse("양념")
public class Class16_annotation {
    public static void main(String[] args){
        Chicken_typeUse[] annotationsByType = Class16_annotation.class.getAnnotationsByType(Chicken_typeUse.class);
        Arrays.stream(annotationsByType).forEach(c -> System.out.println(c.value()));

        ChickenContainer_typeUse annotationContainer = Class16_annotation.class.getAnnotation(ChickenContainer_typeUse.class);
        Arrays.stream(annotationContainer.value()).forEach(c -> System.out.println(c.value()));
    }

}

package com.study.java8;


// 인터페이스의 디폴트 메소드와 스태틱 메소드
public class Class4_defaultMethod_staticMethod {
    public static void main(String[] args){
        DefaultAndStatic defaultAndStatic = new DefaultAndStaticImpl("Jaeheon");
        defaultAndStatic.printName(); // 직접 구현한 메소드
        defaultAndStatic.printNameUpperCase(); // 인터페이스 내 디폴트 메소드.
        DefaultAndStatic.staticPrintName(); // 인터페이스 내 스태틱 메소드.

        // 예외 발생 케이스. 따라서 문서화를 잘 해놔야!
        DefaultAndStatic defaultAndStaticException = new DefaultAndStaticImpl(null);
        defaultAndStaticException.printNameUpperCase();
    }
}

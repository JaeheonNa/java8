package com.study.java8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/*Concurrent 프로그래밍(java8)
    CompletableFuture
    자바에서 비동기(Asynchronous) 프로그래밍을 가능하게 하는 클래스.
    Future와 CompletionStage를 구현함.
    Future의 단점들을 보완함.

    ** Future의 단점
        1. 예외 처리가 안 됨.
        2. 여러 Future를 조합하는 게 안 됨.
        3. get()을 하기 전까지 다른 작업을 할 수 없음.

 */
public class Class14 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*
        Thread 작업 순서를 Chaining 으로 연결하는 방법.
         */
        CompletableFuture<String> cf_1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : Hello!");
            return "Hello!";
        });
        CompletableFuture<String> composedFuture = cf_1.thenCompose(s -> getFuture_1(s));
        System.out.println(composedFuture.get());
    }

    private static CompletableFuture<String> getFuture_1(String s){
        return CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : World!");
            return s + " World!";
        });
    }
}

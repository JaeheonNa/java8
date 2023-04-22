package com.study.java8;

import java.util.ArrayList;
import java.util.List;
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
public class Class14_completableFuture_chaining {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*
        Thread 작업 순서를 Chaining 으로 연결하는 방법.
         -두 Thread 간 연관 관계가 있을 경우.
         */
        System.out.println("#### Thread 작업 순서를 Chaining 으로 연결하는 방법-1 : Start ####");
        System.out.println("#### 두 Thread 간 연관 관계가 있을 경우 ####");
        CompletableFuture<String> cf_1 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : Hello!");
            return "Hello!";
        });
        CompletableFuture<String> composedFuture_1 = cf_1.thenCompose(s -> getFuture_1(s));
        System.out.println(composedFuture_1.get());
        System.out.println("#### Thread 작업 순서를 Chaining 으로 연결하는 방법-1 : End ####");


        System.out.println();

        /*
        Thread 작업 순서를 Chaining 으로 연결하는 방법.
         -두 Thread 간 연관 관계가 없는 경우.
         */
        System.out.println("#### Thread 작업 순서를 Chaining 으로 연결하는 방법-2 : Start ####");
        System.out.println("#### 두 Thread 간 연관 관계가 없는 경우 ####");
        CompletableFuture<String> cf_2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : Hello!");
            return "Hello!";
        });
        CompletableFuture<String> cf_3 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : World!");
            return "World!";
        });
        CompletableFuture<String> cf_4 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : Jaeheon!");
            return "Jaeheon!";
        });
        CompletableFuture<String> combindFuture_1 = cf_2.thenCombine(cf_3, (h, w) -> h + " " + w)
                                                        .thenCombine(cf_4, (hw, n) -> hw + " " + n);
        System.out.println(combindFuture_1.get());
        System.out.println("#### Thread 작업 순서를 Chaining 으로 연결하는 방법-2 : End ####");

        System.out.println();

        /*
        Thread 작업 순서와 관계 없이 한꺼번에 실행하는 방법.
        -모두 끝날 때까지 기다렸다가 결과 반환.
         */
        System.out.println("#### Thread 작업 순서와 관계 없이 한꺼번에 실행하는 방법-1 : Start ####");
        System.out.println("#### 모두 끝날 때까지 기다렸다가 결과 반환하는 경우 ####");
        CompletableFuture<String> cf_5 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " : Hello!");
            return "Hello!";
        });
        CompletableFuture<String> cf_6 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : World!");
            return "World!";
        });

        CompletableFuture<String>[] cfs = new CompletableFuture[2]; // List로 해도 됨.
        cfs[0] = cf_5;
        cfs[1] = cf_6;
        CompletableFuture<List<String>> allResults = CompletableFuture.allOf(cfs).thenApply(r ->{
            List<String> cfList = new ArrayList<>();
            for (CompletableFuture<String> cf : cfs) cfList.add(cf.join());
            return cfList;
        });
        allResults.get().stream().forEach(System.out::println);
        System.out.println("#### Thread 작업 순서와 관계 없이 한꺼번에 실행하는 방법-1 : End ####");

        System.out.println();

        /*
        Thread 작업 순서와 관계 없이 한꺼번에 실행하는 방법.
        -가장 먼저 끝나는 작업 반환.
         */
        System.out.println("#### Thread 작업 순서와 관계 없이 한꺼번에 실행하는 방법-2 : Start ####");
        CompletableFuture<String> cf_7 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : Hello!");
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Hello!";
        });
        CompletableFuture<String> cf_8 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : World!");
            return "World!";
        });
        CompletableFuture<String>[] cfs_1 = new CompletableFuture[2]; // List로 해도 됨.
        cfs_1[0] = cf_7;
        cfs_1[1] = cf_8;
        CompletableFuture<Void> result = CompletableFuture.anyOf(cfs_1).thenAccept(System.out::println);
        result.get();
        System.out.println("#### Thread 작업 순서와 관계 없이 한꺼번에 실행하는 방법-2 : End ####");
    }

    private static CompletableFuture<String> getFuture_1(String s){
        return CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : World!");
            return s + " World!";
        });
    }
}

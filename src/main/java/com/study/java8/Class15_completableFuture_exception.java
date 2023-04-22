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
public class Class15_completableFuture_exception {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        boolean isError = false;

        /*
        에러를 컨트롤 하는 방법-1
         */
        System.out.println("#### 에러를 컨트롤 하는 방법-1 : Start ####");
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            if (isError) throw new IllegalArgumentException();
            return "No Error!";
        }).exceptionally(error -> {
            System.out.println(error);
            return "Error!";
        });
        System.out.println(exceptionally.get());
        System.out.println("#### 에러를 컨트롤 하는 방법-1 : Start ####");
        System.out.println();

        /*
        에러를 컨트롤 하는 방법-1
         */
        System.out.println("#### 에러를 컨트롤 하는 방법-2 : Start ####");
        CompletableFuture<String> handle = CompletableFuture.supplyAsync(() -> {
            if (isError) throw new IllegalArgumentException();
            return "No Error!";
        }).handle((result, error) -> {
            if(error != null){
                System.out.println(error);
                return "Error!";
            } else {
                return result;
            }
        });
        System.out.println(handle.get());
        System.out.println("#### 에러를 컨트롤 하는 방법-2 : Start ####");
        System.out.println();
    }
}

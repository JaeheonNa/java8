package com.study.java8;

import java.util.concurrent.*;


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
public class Class13 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("#### CompletableFuture에 기본 값 주기 : Start ####");
        CompletableFuture<String> cf_1 = new CompletableFuture<>();
        cf_1.complete(Thread.currentThread().getName() + " : complete()"); // <-- future의 기본값을 정해 줄 수 있음.
        CompletableFuture<String> cf_2 = CompletableFuture.completedFuture(Thread.currentThread().getName() + " : completedFuture()"); // <-- future의 기본값을 정해 줄 수 있음.
        System.out.println(cf_1.get());
        System.out.println(cf_2.get());
        System.out.println("#### CompletableFuture에 기본 값 주기 : End ####");

        System.out.println();

        System.out.println("#### 리턴이 없는 CompletableFuture : Start ####");
        CompletableFuture<Void> cf_3 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " : runAsync()");
        });
        cf_3.get();
        System.out.println("#### 리턴이 없는 CompletableFuture : End ####");

        System.out.println();

        System.out.println("#### 리턴이 있는 CompletableFuture : Start ####");
        CompletableFuture cf_4 = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName() + " : supplyAsync()";
        });
        System.out.println(cf_4.get());
        System.out.println("#### 리턴이 있는 CompletableFuture : End ####");

        System.out.println();

        System.out.println("#### 리턴이 없는 CompletableFuture의 후속 작업(콜백) : Start ####");
        CompletableFuture cf_5 = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName() + " : supplyAsync()"; // <-- 이 작업은 sub 스레드에서 작업.
        }).thenAccept((s) -> {
            System.out.println(s + "\n" + Thread.currentThread().getName() + " : thenAccept()"); // <-- 이 작업은 main 스레드에서 작업.
        });
        cf_5.get();
        System.out.println("#### 리턴이 없는 CompletableFuture의 후속 작업(콜백) : End ####");

        System.out.println();

        System.out.println("#### 리턴이 있는 CompletableFuture의 후속 작업(콜백) : Start ####");
        CompletableFuture cf_6 = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName() + " : supplyAsync()"; // <-- 이 작업은 sub 스레드에서 작업.
        }).thenApply((s) -> {
            return  s + "\n" + Thread.currentThread().getName() + " : thenApply()"; // <-- 이 작업은 main 스레드에서 작업.
        });
        System.out.println(cf_6.get());
        System.out.println("#### 리턴이 있는 CompletableFuture의 후속 작업(콜백) : End ####");

        System.out.println();

        System.out.println("#### 리턴 값과 상관 없는 CompletableFuture의 후속 작업(콜백) : Start ####");
        CompletableFuture<Void> cf_7 = CompletableFuture.supplyAsync(() -> {
            return Thread.currentThread().getName() + " : supplyAsync()"; // <-- 리턴 안 됨.
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName() + " : thenRun()");
        });
        cf_7.get();
        System.out.println("#### 리턴 값과 상관 없는 CompletableFuture의 후속 작업(콜백) : End ####");

        System.out.println();
        /*
        어떻게 Thread Pool을 만들지 않고도 별도의 스레드 풀에서 스레드를 가져와 동작하는 걸까?
        ForkJoinPool(Java7) 덕분. ExecutorService의 구현체 중 하나.
        작업할 때 Queue와 함께 Deque를 사용. (Deque는 나중에 들어온 게 먼저 나감).
        Queue에 기다리는 작업이 있으면 thread가 해당 작업을 가져와 쪼개어 자신의 Deque에 넣고 처리.
        또 다른 thread가 할 일이 없을 때 다른 thread의 Deque에서 일을 뺏어와서 처리함.
        A라는 작업이 쪼개져서 Deque에 쌓이기 때문에 Fork.
        여러 스레드가 쪼개진 A의 작업을 각각 처리하고, A의 모든 작업들이 끝나면 결과를 합치기 때문에 Join.
        그래서 ForkJoin.

        하지만 원한다면 ForkJoinPool이 아닌 다른 thread pool을 만들어서 사용하게 할 수도 있음.
        supplyAsync()의 두 번째 인자로 ExecutorService를 주면 됨.

        thread의 이름을 확인해 보면,
        원래 'ForkJoinPool.commonPool-worker-n'이던 스레드의 이름이
        'pool-n-thread-n'으로 바뀐 걸 알 수 있음.
         */

        System.out.println("#### CompletableFuture에게 thread pool 주기-1 : Start ####");
        ExecutorService es_1 = Executors.newFixedThreadPool(4);
        CompletableFuture cf_8 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : runAsync()");
        }, es_1).thenRun(()->{
            System.out.println(Thread.currentThread().getName() + " : thenRun()");
        });
        cf_8.get();
        es_1.shutdown();
        System.out.println("#### CompletableFuture에게 thread pool 주기-1 : End ####");

        System.out.println();

        /* 후속 작업의 사용 thread pool
        then...Async()
            -> ExecutorService(o) : 해당 threadPool 사용.
            -> ExecutorService(x) : ForkJoinPool 사용.
        then...()
            -> ExecutorService(o) : 불가능.
            -> ExecutorService(x) : main thread 사용.
         */
        System.out.println("#### CompletableFuture에게 thread pool 주기-2 : Start ####");
        ExecutorService es_2 = Executors.newFixedThreadPool(4);
        CompletableFuture cf_9 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : runAsync()");
        }, es_2).thenRunAsync(()->{
            System.out.println(Thread.currentThread().getName() + " : thenRunAsync()");
        });
        cf_9.get();
        es_2.shutdown();
        System.out.println("#### CompletableFuture에게 thread pool 주기-2 : End ####");

        System.out.println();

    }
}

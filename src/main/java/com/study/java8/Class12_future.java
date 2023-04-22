package com.study.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;


/*Concurrent 프로그래밍(preJava8)
    Callable & Future
    Callable : return type이 void인 Runnable과 달리 return값을 받을 수 있음.
    Future : 비동기적인 작업의 현재 상태를 조회하거나 결과를 가져올 수있음.
 */
public class Class12_future {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es_1 = Executors.newFixedThreadPool(2);
        Future<String> basic = es_1.submit(getCallable("Callable basic")); // <-- sub thread 실행

        // main thread가 출력
        System.out.println("####Callable basic Started!####");

        // main thread가 sub thread의 결과를 받을 때까지 기다렸다가 출력.
        // (원래 Runnable 객체를 쓸 땐, main thread는 main thread대로, sub thread는 sub thread대로 각각 동작.)
        System.out.println(basic.get());

        // main thread가 출력
        System.out.println("####Callable basic End!####");
        es_1.shutdown();

        System.out.println();

        ExecutorService es_2 = Executors.newSingleThreadExecutor();
        Future<String> isDone = es_2.submit(getCallable("Callable isDone() "));
        System.out.println("####Callable isDone() Started!####");
        // sub thread 작업이 끝났는지 여부를 return.
        System.out.println("Callable isDone() : " + isDone.isDone());
        System.out.println(isDone.get());
        System.out.println("Callable isDone() : " + isDone.isDone());
        System.out.println("####Callable isDone() End!####");
        es_2.shutdown();

        System.out.println();

        ExecutorService es_3 = Executors.newSingleThreadExecutor();
        Future<String> cancel = es_3.submit(getCallable("Callable cancel() "));
        System.out.println("####Callable cancel() Started!####");

        // sub thread의 작업을 취소.
        // true는 당장 취소. false는 작업을 다 할 때까지 기다렸다가 취소.
        cancel.cancel(false);

        // cancel을 하고 나면 isDone()은 무조건 true가 뜸. 작업이 완료(취소)됐다는 뜻.
        System.out.println("Callable cancel() isDone : " + cancel.isDone());

        // cancel하면 작업이 취소됐기 때문에 값을 가져올 수 없음.
        // cancel 후 get하려고 하면 CancellationException 발생.
        // shutdown() 전에 Exception이 발생하면, shutdown()이 동작하지 않았기 때문에, 프로세스 종료가 안 됨.
//        System.out.println(cancel.get());
        System.out.println("####Callable cancel() End!####");
        es_3.shutdown();

        System.out.println();


        Callable<String> hello = () -> {
            Thread.sleep(1000);
            return Thread.currentThread().getName() + " Hello";
        };
        Callable<String> java = () -> {
            Thread.sleep(2000);
            return Thread.currentThread().getName() + " Java";
        };
        Callable<String> spring = () -> {
            Thread.sleep(3000);
            return Thread.currentThread().getName() + " Spring";
        };

        // invokeAll() : 여러 개의 Callable을 동시에 실행
        // 모든 Callable이 끝날 때까지 기다렸다가 get()해옴.
        ExecutorService es_4 = Executors.newFixedThreadPool(3);
        List<Future<String>> futures = es_4.invokeAll(Arrays.asList(java, spring, hello));
        System.out.println("####Multiple Callable Started!####");
        futures.stream().forEach(f ->{
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("####Multiple Callable End!####");
        es_4.shutdown();

        System.out.println();

        // invokeAny() : 여러 개의 Callable을 동시에 실행.
        // 가장 먼저 끝난 것을 리턴.
        ExecutorService es_5 = Executors.newFixedThreadPool(3);
        String futureList = es_5.invokeAny(Arrays.asList(java, spring, hello));
        System.out.println("####Multiple Callable Started!####");
        System.out.println(futureList);
        System.out.println("####Multiple Callable End!####");
        es_5.shutdown();



    }

    public static <T> Callable<T> getCallable(T name){
        return () -> {
            Thread.sleep(1000L);
            return (T) ("Hello, " + name + "!");
        };
    }
}

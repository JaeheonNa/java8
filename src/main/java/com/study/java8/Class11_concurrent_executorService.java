package com.study.java8;

import java.util.concurrent.*;


/*Concurrent 프로그래밍(java8)
    Executors : 고수준의 Concurrency 프로그래밍. 기존보다 더 추상화됐다는 뜻.
    스레드 생성. 스레드 풀을 만들어 관리함.
    스레드 관리. 스레드 생명 주기를 관리함.
    작업 처리 및 실행. 스레드로 실행할 작업을 제공할 수 있는 API를 제공.

    Executor 별로 관리하는 스레드 풀이 다름. 당연히 관리하는 스레드도 다름.
 */
public class Class11_concurrent_executorService {
    public static void main(String[] args) throws InterruptedException {
        // Executor 관련 객체는 Executors에서 생성함.

        // Executor는 Runnable 객체를 받아 실행하는 execute() 메소드 밖에 없음.
        // 그래서 보통은 Executor를 확장한 ExecutorService를 많이 사용.
        // ExecutorService를 확장한 ScheduledExecutorService도 있음.
        // 작업을 딜레이 시키거나 주기적으로 실행하는 등의 scheduling 관련 기능들이 추가됨.

        ExecutorService es = Executors.newSingleThreadExecutor(); // <-- 첫 번째 스레드 풀에 스레드가 1.
        es.execute(()-> System.out.println("Thread : " + Thread.currentThread().getName())); // <-- 자기 이름을 출력하는 작업을 수행하는 스레드 실행.
        // 작업을 실행하고 다음 작업이 들어올 때까지 대기를 하기 때문에 프로세스가 죽지 않음.
        // 따라서 명시적으로 끝내줘야.
        // shutdown() : graceful shutdown. 진행 중인 작업을 모두 끝낸 후 종료.
        // shutdownNow() : 진행 중인 작업 유무와 관계없이 종료.
        es.shutdown();

        // ExecutorService가 스레드풀에 갖고 있는 스레드를 알아서 활용.
        ExecutorService ess = Executors.newFixedThreadPool(2); // <-- 두 번째 스레드풀에 스레드가 2.
        ess.execute(getRunnable("Jaeheon"));
        ess.execute(getRunnable("KyoungJin"));
        ess.execute(getRunnable("Loo"));
        ess.execute(getRunnable("Na"));
        ess.execute(getRunnable("Moon"));
        ess.shutdown();

        // thread 작업 딜레이 시키기.
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor(); // <-- 세 번째 스레드풀에 스레드가 1.
        ses.schedule(getRunnable("Delayer"), 2, TimeUnit.SECONDS);
        ses.shutdown();

        // thread 작업 주기적으로 반복 시키기.
        ScheduledExecutorService sesIter = Executors.newSingleThreadScheduledExecutor();
        sesIter.scheduleAtFixedRate(getRunnable("Iterator"), 1, 1, TimeUnit.SECONDS);


        System.out.println("I'm main thread!");
        ExecutorService finalEs = Executors.newSingleThreadExecutor();
        finalEs.execute(() -> {
            try {
                Thread.sleep(3000L);
                System.out.println("I'm sub thread!");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("I'm main thread!");
    }

    public static Runnable getRunnable(String name){
        return () -> System.out.println(name + "'s Thread : " + Thread.currentThread().getName());
    }
}

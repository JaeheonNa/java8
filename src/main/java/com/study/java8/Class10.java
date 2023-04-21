package com.study.java8;

// Concurrent 프로그래밍(java8 이전)
/*
    Concurrent 소프트웨어란, 동시에 여러 작업을 할 수 있는 소프트웨어.
    Java에서는 멀티 프로세싱과 멀티 스레드로 지원.
    멀티 스레딩은 실행 순서 보장이 안 됨.
    아래를 보면 알겠지만 Java8 이전의 멀티스레딩 코드는 너무 복잡함.
 */
public class Class10 {
    public static void main(String[] args) throws InterruptedException {
        /*
            멀티 스레딩을 하는 첫 번째 방법.
         */
        System.out.println(Thread.currentThread().getName()); // <-- main 스레드.
        MyThread myThread = new MyThread(); // <-- main 스레드가 서브 스레드를 생성.
        myThread.start(); // <-- 서브 스레드.
        System.out.println(Thread.currentThread().getName()); // <-- main 스레드.

        /*
            멀티 스레딩을 하는 두 번째 방법.
            Runnable을 Thread에 주입해서 사용. (Runnable은 functional interface. 람다로 표현 가능)
         */
        Thread runnableThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        runnableThread.start();

        /*
            스레드를 재우는 방법. & 종료시키는 방법.
         */
        Thread runnableThread_1 = new Thread(() -> {
            try {
                Thread.sleep(1000L); // <-- 스레드를 잠시 재울 수 있음.
            } catch (InterruptedException e) {
                System.out.println("Exit!");
                return;
            }
            System.out.println(Thread.currentThread().getName());
        });
        runnableThread_1.start();
        Thread.sleep(3000L);
        runnableThread_1.interrupt(); // <-- 스레드를 종료시키는 방법.

        /*
            다른 스레드가 일을 종료하고 해당 라인까지 올 때까지 기다리는 방법
         */
        Thread runnableThread_2 = new Thread(() -> {
            try {
                System.out.println("Sub Thread \"" + Thread.currentThread().getName()+ " is started.\"");
                Thread.sleep(3000L); // <-- 스레드를 잠시 재울 수 있음.
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });
        runnableThread_2.start();
        runnableThread_2.join(); // <-- 스레드가 앞선 작업을 끝낼 때까지 기다리는 방법. 메인스레드가 자기 일을 하지 않고, 기다림.
        System.out.println("Main Thread \"" + runnableThread_2.getName() + " is finished.\"");
        System.out.println(Thread.currentThread().getName());

    }


    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName());
        }
    }
}

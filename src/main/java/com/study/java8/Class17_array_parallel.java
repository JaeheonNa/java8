package com.study.java8;

/* 배열 parallel 정렬
    배열 정렬을 병렬로 처리.
    멀티 스레드로 동작하기 때문에
    공간 효율은 떨어지지만
    시간 효율은 좋아짐.
    총 효율은 같음.

    배열을 둘로 계속 쪼갠 후 합치면서 정렬.

    ** 단, 항상 parallel sort가 빠른 건 아님.
    sorting 해야 할 대상이 적으면 serial sort가 더 빠름.
    대상의 수는 컴퓨터의 성능에 따라 다름.
    내 컴퓨터의 경우 최소 30만을 넘겨야 parallel sort가 더 빠른 듯.
 */

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Class17_array_parallel {
    public static void main(String[] args){
        int size = 300000;
        int[] numbers = new int[size];
        Random random = new Random();

         // 무작위 순의 숫자 배열 생성.
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        // 시작 시간 체크.
        long startTime_serial = System.nanoTime();
        // 정렬.
        Arrays.sort(numbers);
        // 종료 시간 체크 후 총 runtime 출력.
        System.out.println("Serial Sorting took [" + (System.nanoTime() - startTime_serial) + "] nano seconds");

        // 무작위 순의 숫자 배열 생성.
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        // 시작 시간 체크.
        long startTime_parallel = System.nanoTime();
        // 정렬.
        Arrays.parallelSort(numbers);
        // 종료 시간 체크 후 총 runtime 출력.
        System.out.println("Parallel Sorting took [" + (System.nanoTime() - startTime_parallel) + "] nano seconds");


    }

}

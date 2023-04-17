package com.study.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Stream
// 연속된 데이터를 처리하는 operation들의 모음.
// Stream은 한 번만 데이터를 처리함.
public class Class6 {
    public static void main(String[] args){
        List<String> names = new ArrayList<>();
        names.add("IronMan");
        names.add("SpiderMan");
        names.add("Javis");
        names.add("Tanos");
        names.add("CaptinAmerica");
        names.add("BlackWidow");
        names.add("Thor");

        // stream() 내 operator는 '중계 operator'과 '종료 operator'으로 나뉨.
        // 중계 operator는 Stream 객체를 리턴함.
        // 중계 operator는 Lazy 함. Lazy하다는 것은 '종료 operator'를 만나기 전까진 실행을 안 한다는 것.
        // stream()은 '종료 operator'가 반드시 하나 있어야 함.
        names.stream().map((s)-> {
            System.out.println(s);
            return s.toLowerCase();
        });
        // 위 코드를 실행하면 콘솔 출력 안 함. '종료 operator'가 없기 때문.

        // 반면, 아래 코드는 정상 실행 됨. '종료 operator(collect())가 있기 때문.
        List<String> collect1 = names.stream()
                                    .map((s) -> {
                                        System.out.println(s.toUpperCase());
                                        return s.toUpperCase();
                                    }).collect(Collectors.toList());

        // parallelStream()을 쓰면 멀티쓰레드 병렬 처리도 가능하다!
        // 항상 병렬 처리가 빠른 것은 아님. 컨텍스트 스위칭 등의 비용이 추가 발생할 수 있기 때문.
        // 정말 데이터가 방대할 때 유용.
        List<String> collect2 = names.parallelStream()
                .map((s) -> {
                    System.out.println(s.toUpperCase());
                    return s.toUpperCase();
                }).collect(Collectors.toList());

        /*
        - 중계 operator: filter(), map(), limit(), skip(), sorted() ...
        - 종료 operator: collect(), allMatch(), count(), forEach(), min(), max() ...
         */


    }
}

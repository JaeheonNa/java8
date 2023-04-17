package com.study.java8;

import java.util.*;

// Java에서 제공하는 디폴트 메소드와 스태틱 메소드
public class Class5 {
    public static void main(String[] args){
        List<String> names = new ArrayList<>();
        names.add("IronMan");
        names.add("SpiderMan");
        names.add("Javis");
        names.add("Tanos");
        names.add("CaptinAmerica");
        names.add("BlackWidow");
        names.add("Thor");

        // List의 forEach() 함수는 인자로 Consumer를 받음.
        // new Consumer<String>(){}으로 직접 구현해도 됨.
        // Consumer는 함수형 인터페이스이므로 그냥 람다 혹은 레퍼런스 메소드로 ㄱㄱ.
        System.out.println("==forEach(Lambda)==");
        names.forEach((name) -> System.out.println(name)); // Lambda ver.
        System.out.println("====");

        System.out.println("==forEach(Reference Method)==");
        names.forEach(System.out::println);                // Reference Ver.
        System.out.println("====");

        // removeIf()는 Predicate를 인자로 받음.
        System.out.println("==removeIf(Lambda)==");
        names.removeIf((name) -> name.startsWith("T"));
        names.forEach(System.out::println);
        System.out.println("====");

        // sort()는 Comparator를 인자로 받음.
        // Comparator는 <T, T, R>. 두 개의 같은 타입의 인자를 받아서, int를 반환하는 함수형 인터페이스.
        System.out.println("==Sort(Lambda)==");
        names.sort((a, b) -> a.compareToIgnoreCase(b));
        names.forEach(System.out::println);
        System.out.println("====");

        System.out.println("==Sort(Reference Method)==");
        Comparator<String> string = String::compareToIgnoreCase; // String 클래스의 compareToIgnoreCase 메소드를 Comparator함수형 인터페이스의 구현체로 쓰겠다.
        names.sort(string.reversed()); // 역순으로 정렬.
        names.forEach(System.out::println);
        System.out.println("====");

        /*
            Comparator 메서드.
            reverse()
            thenComparing()
            static reverseOrder()/naturalOrder()
            static nullsFirst()/nullsLast()
            static comparing()
        */


        // List의 spliterator()는 iterator와 유사.
        // 쪼개는 기능을 가지고 있는 iterator.
        // Spliterator의 tryAdvance()역시 Consumer를 인자로 받음.
        System.out.println("==Splitorator.tryAdvance()==");
        Spliterator<String> spliterator = names.spliterator();
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("====");
        /*  Iterator로 구현한 경우.
            Iterator<String> iterator = names.iterator();
            while(iterator.hasNext()) System.out.println(iterator.next());
            System.out.println("==");
         */

        // Spliterator의 trySpliter는 Spliterator 인스턴스를 반으로 쪼갬.
        // 아래의 코드에서 names 리스트를 spliterator1과 spliterator2가 반반 씩 가짐.
        // 홀수일 경우 원래 인스턴스가 하나 더 가져감.
        // stream()은 Spliterator로 구현됨.
        System.out.println("==Splitorator.trySplit()==");
        Spliterator<String> spliterator1 = names.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        while(spliterator1.tryAdvance(System.out::println));
        System.out.println("==");
        while(spliterator2.tryAdvance(System.out::println));


    }
}

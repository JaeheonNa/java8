package com.study.java8;

import com.study.java8.marvel.Ability;
import com.study.java8.marvel.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Optional
// 비어있을 수도 있고, 무언가를 담고 있을 수도 있는 컨테이너 인스턴스 타입.
// Optional 객체는 가급적 리턴 타입으로만 사용할 것.
// Map의 key를 Optional로 하지 말 것. Map의 key는 null이면 안 됨.
// Primitive type용 Optional은 따로 있음. ex) OnptionalInt, OptionalLong ,,,
// Optional이 리턴 타입인 메소드에서 null을 리턴하지 말라. 필요하다면 Optional.empty() 리턴.
// Collection, Map, Stream, Optional은 Optional로 감싸지 말 것.
public class Class8 {
    public static void main(String[] args){
        Hero ironMan = new Hero("IronMan", "Avengers", "Human", "M");
        Hero spiderMan = new Hero("SpiderMan", "Avengers", "Human", "M");
        Hero thor = new Hero("Thor", "Avengers", "God", "M");
        Hero tanos = new Hero("Tanos", "Titan", "Alien", "M");
        Hero javis = new Hero("Javis", "Avengers", "AI", "W");
        Hero blackWidow = new Hero("BlackWidow", "Avengers", "Human", "W");

        List<Hero> marvel = new ArrayList<>();
        marvel.add(ironMan);
        marvel.add(spiderMan);
        marvel.add(thor);
        marvel.add(tanos);
        marvel.add(javis);
        marvel.add(blackWidow);

        Ability spiderWeb = new Ability("SpiderWeb", 6);
        Ability thunder = new Ability("Thunder", 9);
        marvel.get(1).setAbilities(spiderWeb);
        marvel.get(2).setAbilities(thunder);

        // 아래 코드는 NullPointerException 발생.
        // ability를 setting해준 적이 없으므로. null 체크 필요했음.
        /*
            List<Ability> abilities = marvel.get(0).getAbilities();
            System.out.println(abilities.get(0).getName());
         */

        // 모든 코드에 일일이 null 체크를 할 필요가 없도로 java8에서는 Optional이란 녀석을 제공.
        Optional<Ability> ability0 = marvel.get(0).getAbilities();
        System.out.println(marvel.get(0).getName() + "'s ability is...");
        if(ability0.isPresent()) System.out.println(ability0.get().getName());
        else System.out.println("nothing.");
        System.out.println("==");

        Optional<Ability> ability1 = marvel.get(1).getAbilities();
        System.out.println(marvel.get(1).getName() + "'s ability is...");
        if(ability1.isPresent()) System.out.println(ability1.get().getName());
        else System.out.println("nothing.");
        System.out.println("==");

        Optional<Ability> ability2 = marvel.get(2).getAbilities();
        System.out.println(marvel.get(2).getName() + "'s ability is...");
        if(ability2.isPresent()) System.out.println(ability2.get().getName());
        else System.out.println("nothing.");
        System.out.println("==");

        /* Optional 만들기
            Optional.of()
            Optional.ofNullable()
            Optional.empty()
        */

        /* Optional의 값 존재 확인하기
            isPresent()
            isEmpty()
        */

        /* Optional 값 꺼내기.
            get() --단, 값 존재를 확인하고 거내는 것을 강력 권고.
            orElse()
            orElseGet()
            orElseThrow()
        */

        Optional<Hero> hero = marvel.stream()
                                .filter(h -> h.getName().startsWith("T"))
                                .findFirst();

        hero.ifPresent(h -> System.out.println("ifPresent() : " + h.getName())); // 존재하면, consumer 수행.
        Hero hero_1 = hero.orElse(getUnknownHero()); // 존재하면 Hero을 꺼내고, 다른 Hero를 꺼냄.
        System.out.println("orElse() : " + hero_1.getName());
        Hero hero_2 = hero.orElseGet(Class8::getUnknownHero); // 존재하면 Hero를 꺼내고, 존재하지 않으면 메서드 실행.
        System.out.println("orElseGet() : " + hero_2.getName());
        Hero hero_3 = hero.orElseThrow(() -> new RuntimeException()); // 존재하면 Hero를 꺼내고, 존재하지 않으면 예외.
        System.out.println("orElseThrow() : " + hero_3.getName());

    }

    public static Hero getUnknownHero(){
        Hero unknown = new Hero("unknown", "unknown", "unknown", "unknown");
        return unknown;
    }
}

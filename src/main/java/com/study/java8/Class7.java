package com.study.java8;

import com.study.java8.marvel.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Stream 연습
public class Class7 {
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

        // filter : 조건에 맞게 필터링
        System.out.println("==filter(어벤져스만 출력)==");
        marvel.stream()
                .filter(h -> h.getTeam().equals("Avengers"))
                .forEach(h -> System.out.println(h.getName()));
        System.out.println("====");

        // map : 데이터 변환
        System.out.println("==map(여자만 출력)==");
        marvel.stream()
                .filter(h -> h.getGender().equals("W"))
                .map(h -> h.getName())
                .forEach(System.out::println);
        System.out.println("====");

        List<Hero> man = new ArrayList<>();
        man.add(ironMan);
        man.add(spiderMan);
        man.add(thor);
        man.add(tanos);
        List<Hero> woman = new ArrayList<>();
        woman.add(javis);
        woman.add(blackWidow);

        List<List<Hero>> marvelHeroList = new ArrayList<>();
        marvelHeroList.add(man);
        marvelHeroList.add(woman);

        // flatMap : 리스트 안의 리스트를 쭈욱 펴줌.
        System.out.println("==flatMap(Human만 출력)==");
        marvelHeroList.stream()
                .flatMap((l) -> l.stream())
                .filter(h -> h.getSpecies().equals("Human"))
                .map(Hero::getName)
                .forEach(System.out::println);
        System.out.println("====");

        // allMatch
        System.out.println("==allMatch==");
        boolean f = marvelHeroList.stream()
                .flatMap(list -> list.stream())
                .allMatch(h -> h.getName().startsWith("T"));
        System.out.println("All hero names starts with 'T' = " + f );
        System.out.println("====");

        // anyMatch
        System.out.println("==anyMatch==");
        boolean t = marvelHeroList.stream()
                .flatMap(list -> list.stream())
                .anyMatch(h -> h.getName().startsWith("T"));
        System.out.println("Some hero names starts with 'T' = " + t );
        System.out.println("====");

        // collect
        System.out.println("==anyMatch==");
        List<String> speciesList = marvelHeroList.stream()
                .flatMap(list -> list.stream())
                .map(h -> h.getSpecies())
                .collect(Collectors.toList());
        for (String s : speciesList) System.out.println(s);
        System.out.println("====");

        // iterate : 무한 반복자.
        // skip : 건너뛰기 .
        // limit : 리밋 수.
        System.out.println("==iterate==");
        Stream.iterate(0, i -> i + 1)
                .skip(10)
                .limit(20)
                .forEach(System.out::println);
        System.out.println("====");


    }
}

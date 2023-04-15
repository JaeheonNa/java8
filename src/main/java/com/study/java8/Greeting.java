package com.study.java8;

public class Greeting {
    private String name;
    public Greeting(){

    }
    public Greeting(String name){
        this.name = name;
    }


    public String hello(String name){
        if(this.name != null) return this.name + "\" hello " + name + "\"";
        else return "Unknown \"hello " + name + "\"";
    }

    public static String hi(String name){
        return "hi " + name;
    }
}

package com.study.java8;

public class DefaultAndStaticImpl implements DefaultAndStatic{

    private String name;

    public DefaultAndStaticImpl(String name){
        this.name = name;
    }
    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }
}

package org.journerist.wildfly.tokenBasedAuthentification;

public class POJO {

    String name;
    int age;

    public POJO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age + 2;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

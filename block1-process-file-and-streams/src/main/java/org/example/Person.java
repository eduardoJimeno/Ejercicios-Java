package org.example;

public class Person{
    private String name, town;
    private int age;

    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: "+ name +
                ". Town:" + (town.isEmpty() ? "unknown" : town) +
                ". Age:" + (age==0 ? "unknown" : age);
    }
}

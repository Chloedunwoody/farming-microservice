package com.dunwoody.api.composite.barn;

public class HorseSummary {
    private final int horseId;
    private final String name;
    private final int age;

    public HorseSummary(){
        horseId=0;
        name=null;
        age=0;
    }

    public HorseSummary(int horseId, String name, int age) {
        this.horseId = horseId;
        this.name = name;
        this.age = age;
    }

    public int getHorseId() { return horseId; }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

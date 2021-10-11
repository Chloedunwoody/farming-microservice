package com.dunwoody.api.core.horse;

public class Horse {
    private final int barnId;
    private final int horseId;
    private final String name;
    private final int age;
    private final String gender;
    private final String breed;
    private final boolean feedAm;
    private final boolean feedPm;
    private final boolean water;
    private final String serviceAddress;

    public Horse(int barnId, int horseId, String name,int age, String gender, String breed, boolean feedAm, boolean feedPm, boolean water, String serviceAddress) {
        this.barnId = barnId;
        this.horseId = horseId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.feedAm = feedAm;
        this.feedPm = feedPm;
        this.water = water;
        this.serviceAddress = serviceAddress;
    }

    public Horse() {
        barnId=0;
         horseId= 0;
         name=null;
         age= 0;
         gender=null;
         breed=null;
         feedAm=false;
         feedPm=false;
         water=false;
         serviceAddress=null;
    }

    public int getBarnId() {
        return barnId;
    }

    public int getHorseId() {
        return horseId;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBreed() {
        return breed;
    }

    public boolean isFeedAm() {
        return feedAm;
    }

    public boolean isFeedPm() {
        return feedPm;
    }

    public boolean isWater() {
        return water;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }


}

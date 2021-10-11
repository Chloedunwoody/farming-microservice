package com.dunwoody.api.core.owner;

public class Owner {

    private final int barnId;
    private final int ownerId;
    private final String name;
    private final int age;
    private final double fee;
    private final String serviceAddress;

    public Owner(){
        barnId=0;
        ownerId=0;
        name=null;
        age=0;
        fee=0.0;
        serviceAddress=null;
    }

    public Owner(int barnId,int ownerId, String name, int age, double fee, String serviceAddress) {
        this.barnId =barnId;
        this.ownerId = ownerId;
        this.name = name;
        this.age = age;
        this.fee = fee;
        this.serviceAddress = serviceAddress;
    }

    public int getBarnId() {
        return barnId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getFee() {
        return fee;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
}

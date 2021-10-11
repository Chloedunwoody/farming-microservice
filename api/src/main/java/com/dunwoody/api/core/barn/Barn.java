package com.dunwoody.api.core.barn;

public class Barn {

    private final int barnId;
    private final String name;
    private final String address;
    private final int capacity;
    private final String serviceAddress;

    public Barn() {
        barnId=0;
        name=null;
        address=null;
        capacity=0;
        serviceAddress=null;
    }

    public Barn(int barnId, String name, String address, int capacity, String serviceAddress) {
        this.barnId = barnId;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.serviceAddress = serviceAddress;
    }

    public int getBarnId() {
        return barnId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
}

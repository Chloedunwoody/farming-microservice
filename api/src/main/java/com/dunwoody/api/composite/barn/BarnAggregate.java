package com.dunwoody.api.composite.barn;

import java.util.List;

public class BarnAggregate {
    private final int barnId;
    private final String name;
    private final String address;
    private final int capacity;
    private final List<HorseSummary> horses;
    private final List<OwnerSummary> owners;
    private final ServiceAddress serviceAddress;

    public BarnAggregate() {
        barnId =0;
        name = null;
        address =null;
        capacity=0;
        horses=null;
        owners=null;
        serviceAddress=null;
    }

    public BarnAggregate(int barnId, String name, String address, int capacity, List<HorseSummary> horses, List<OwnerSummary> owners, ServiceAddress serviceAddress) {
        this.barnId = barnId;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.horses = horses;
        this.owners = owners;
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

    public List<HorseSummary> getHorses() {
        return horses;
    }

    public List<OwnerSummary> getOwners() {
        return owners;
    }

    public ServiceAddress getServiceAddress() {
        return serviceAddress;
    }
}

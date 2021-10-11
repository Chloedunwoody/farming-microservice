package com.dunwoody.api.composite.barn;

public class ServiceAddress {
    private final String compositeAddress;
    private final String barnAddress;
    private final String horseAddress;
    private final String ownerAddress;

    public ServiceAddress() {
        compositeAddress=null;
        barnAddress=null;
        horseAddress=null;
        ownerAddress=null;
    }

    public String getCompositeAddress() {
        return compositeAddress;
    }

    public String getBarnAddress() {
        return barnAddress;
    }

    public String getHorseAddress() {
        return horseAddress;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public ServiceAddress(String compositeAddress, String barnAddress, String horseAddress, String ownerAddress) {
        this.compositeAddress = compositeAddress;
        this.barnAddress = barnAddress;
        this.horseAddress = horseAddress;
        this.ownerAddress = ownerAddress;
    }
}

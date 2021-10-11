package com.dunwoody.api.composite.barn;

public class OwnerSummary {
    private final int ownerId;
    private final String name;

    public OwnerSummary(){
        ownerId=0;
        name=null;
    }

    public OwnerSummary(int ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }
}

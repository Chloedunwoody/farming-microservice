package com.dunwoody.farm.composite.barn.getBarn.presentationlayer.controllers;

import com.dunwoody.api.composite.barn.*;
import com.dunwoody.api.core.barn.Barn;
import com.dunwoody.api.core.horse.Horse;
import com.dunwoody.api.core.owner.Owner;
import com.dunwoody.farm.composite.barn.getBarn.integrationlayer.BarnCompositeIntegration;
import com.dunwoody.utils.exceptions.NotFoundException;
import com.dunwoody.utils.http.ServiceUtil;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BarnCompositeRESTController implements BarnCompositeServiceAPI {
    private final ServiceUtil serviceUtil;

    private final BarnCompositeIntegration integration;

    public BarnCompositeRESTController(ServiceUtil serviceUtil, BarnCompositeIntegration integration) {
        this.serviceUtil = serviceUtil;
        this.integration = integration;
    }


    @Override
    public BarnAggregate getBarn(int barnId) {
        Barn barn = integration.getBarn(barnId);
        if (barn == null) throw new NotFoundException("No barn found with barnId: " + barnId);
        List<Horse> horses = integration.getHorses(barnId);

        List<Owner> owners = integration.getOwners(barnId);

        return createBarnAggregate(barn, horses, owners, serviceUtil.getServiceAddress());

    }

    private BarnAggregate createBarnAggregate(Barn barn, List<Horse> horses, List<Owner> owners, String serviceAddress) {
        int barnId = barn.getBarnId();
        String name = barn.getName();
        String address = barn.getAddress();
        int capacity = barn.getCapacity();

        List<HorseSummary> horseSummaries = (horses == null) ? null :
                horses.stream()
                        .map(h -> new HorseSummary(h.getHorseId(), h.getName(), h.getAge()))
                        .collect(Collectors.toList());

        List<OwnerSummary> ownerSummaries = (owners == null) ? null :
                owners.stream()
                        .map(o -> new OwnerSummary(o.getOwnerId(), o.getName()))
                        .collect(Collectors.toList());

        String barnAddress = barn.getServiceAddress();
        String horseAddress = (horses != null && horses.size() > 0) ?
                horses.get(0).getServiceAddress() : "";
        String ownerAddress = (owners != null && owners.size() > 0) ?
                owners.get(0).getServiceAddress() : "";

        ServiceAddress serviceAddresses = new ServiceAddress(serviceAddress, barnAddress, horseAddress, ownerAddress);
        return new BarnAggregate(barnId, name, address, capacity, horseSummaries, ownerSummaries, serviceAddresses);
    }
}
package com.dunwoody.farm.core.owner.presentationlayer.controllers;

import com.dunwoody.api.core.horse.Horse;
import com.dunwoody.api.core.owner.Owner;
import com.dunwoody.api.core.owner.OwnerServiceAPI;
import com.dunwoody.utils.exceptions.InvalidInputException;
import com.dunwoody.utils.exceptions.MovedOrPassed;
import com.dunwoody.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OwnerRESTController implements OwnerServiceAPI {
    private static final Logger LOG = LoggerFactory.getLogger(OwnerRESTController.class);

    private final ServiceUtil serviceUtil;

    public OwnerRESTController(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }


    @Override
    public List<Owner> getOwners(int barnId) {
        if(barnId <1) throw new InvalidInputException("Invalid barnId: " +barnId);

        if (barnId == 213) {
            LOG.debug("/barn return the found barn for barnId: " + barnId);
            return new ArrayList<>();

        }
        if (barnId == 4000) throw new MovedOrPassed("Barn has been moved or passed for barnId: " +barnId);

        List<Owner> listOwners = new ArrayList<>();
        listOwners.add(new Owner(barnId,11,"Kim", 23,20.69, serviceUtil.getServiceAddress()));
        listOwners.add(new Owner(barnId, 22,"Chloe",45,50.69,serviceUtil.getServiceAddress() ));
        listOwners.add(new Owner(barnId, 33,"Nico",35,23.69,serviceUtil.getServiceAddress() ));

        LOG.debug("/owner found response size {}", listOwners.size());
        return listOwners;
    }
}

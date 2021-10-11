package com.dunwoody.farm.core.barn.presentationlayer.controllers;

import com.dunwoody.api.core.barn.Barn;
import com.dunwoody.api.core.barn.BarnServiceAPI;
import com.dunwoody.utils.exceptions.InvalidInputException;
import com.dunwoody.utils.exceptions.MovedOrPassed;
import com.dunwoody.utils.exceptions.NotFoundException;
import com.dunwoody.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarnRESTController implements BarnServiceAPI {
    private static final Logger LOG = LoggerFactory.getLogger(BarnRESTController.class);

    private final ServiceUtil serviceUtil;

    public BarnRESTController(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }


    @Override
    public Barn getBarn(int barnId) {
        LOG.debug("/barn return the found barn for barnId: " + barnId);
        if(barnId <1) throw new InvalidInputException("Invalid barnId: " + barnId);
        if(barnId ==13) throw new NotFoundException("No Barn found for barnId: " + barnId);
        if(barnId ==2000) throw new MovedOrPassed("Barn has been moved or passed for barnId: " + barnId);

        return new Barn(barnId, "Shady Meadows", "12 River Rd., CANADA", 45, serviceUtil.getServiceAddress());
    }
}

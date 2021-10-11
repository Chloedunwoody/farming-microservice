package com.dunwoody.farm.core.horse.presentationlayer.controllers;

import com.dunwoody.api.core.horse.Horse;
import com.dunwoody.api.core.horse.HorseServiceAPI;
import com.dunwoody.utils.exceptions.InvalidInputException;
import com.dunwoody.utils.exceptions.MovedOrPassed;
import com.dunwoody.utils.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HorseRESTController implements HorseServiceAPI {
    private static final Logger LOG = LoggerFactory.getLogger(HorseRESTController.class);

    private final ServiceUtil serviceUtil;

    public HorseRESTController(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }


    @Override
    public List<Horse> getHorses(int barnId) {
        if(barnId <1) throw new InvalidInputException("Invalid barnId: " +barnId);

        if (barnId == 113) {
            LOG.debug("/barn return the found barn for barnId: " + barnId);
            return new ArrayList<>();

        }
        if (barnId == 3000) throw new MovedOrPassed("Barn has been moved or passed for barnId: " +barnId);


        List<Horse> listHorses = new ArrayList<>();
        listHorses.add(new Horse(barnId,1, "Shadow",4,"Stallion","Thoroughbred", false, false, false,serviceUtil.getServiceAddress() ));
        listHorses.add(new Horse(barnId, 2,"Ryder",7,"Mare","Shetland", true, true, true,serviceUtil.getServiceAddress() ));
        listHorses.add(new Horse(barnId, 3,"Charlie",1,"Gelding","Warmblood", false, true, false,serviceUtil.getServiceAddress() ));

        LOG.debug("/horse found response size {}", listHorses.size());
        return listHorses;
    }
}

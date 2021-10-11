package com.dunwoody.api.core.owner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OwnerServiceAPI {
    @GetMapping(
            value= "/owner",
            produces = "application/json"
    )
    List<Owner> getOwners(@RequestParam( value = "barnId", required = true) int barnId);
}


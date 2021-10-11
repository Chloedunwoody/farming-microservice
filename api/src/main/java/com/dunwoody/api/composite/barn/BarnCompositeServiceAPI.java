package com.dunwoody.api.composite.barn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BarnCompositeServiceAPI {
    @GetMapping(
            value= "/barn-composite/{barnId}",
            produces = "application/json"
    )
    BarnAggregate getBarn(@PathVariable int barnId);

}

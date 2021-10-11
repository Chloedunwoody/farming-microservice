package com.dunwoody.api.core.barn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface BarnServiceAPI {
    @GetMapping(
            value = "/barn/{barnId}",
            produces = "application/json"
    )

    Barn getBarn(@PathVariable int barnId);
}

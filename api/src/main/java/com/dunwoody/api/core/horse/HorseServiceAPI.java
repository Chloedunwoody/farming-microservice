package com.dunwoody.api.core.horse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface HorseServiceAPI {
    @GetMapping(
            value= "/horse",
            produces = "application/json"
    )
    List<Horse> getHorses(@RequestParam( value = "barnId", required = true) int barnId);

}

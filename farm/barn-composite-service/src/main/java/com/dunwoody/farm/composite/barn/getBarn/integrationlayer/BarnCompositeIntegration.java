package com.dunwoody.farm.composite.barn.getBarn.integrationlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dunwoody.api.core.barn.Barn;
import com.dunwoody.api.core.barn.BarnServiceAPI;
import com.dunwoody.api.core.horse.Horse;
import com.dunwoody.api.core.horse.HorseServiceAPI;
import com.dunwoody.api.core.owner.Owner;
import com.dunwoody.api.core.owner.OwnerServiceAPI;

import com.dunwoody.utils.exceptions.InvalidInputException;
import com.dunwoody.utils.exceptions.NotFoundException;
import com.dunwoody.utils.http.HttpErrorInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class BarnCompositeIntegration implements BarnServiceAPI, HorseServiceAPI, OwnerServiceAPI{
    private static final Logger LOG = LoggerFactory.getLogger(BarnCompositeIntegration.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String barnServiceUrl;
    private final String horseServiceUrl;
    private final String ownerServiceUrl;

    public BarnCompositeIntegration(RestTemplate restTemplate,
                                    ObjectMapper mapper,

                                    @Value("${app.barn-service.host}") String barnServiceHost,
                                    @Value("${app.barn-service.port}") String barnServicePort,

                                    @Value("${app.horse-service.host}") String horseServiceHost,
                                    @Value("${app.horse-service.port}") String horseServicePort,

                                    @Value("${app.owner-service.host}") String ownerServiceHost,
                                    @Value("${app.owner-service.port}") String ownerServicePort

    ){
        this.restTemplate =restTemplate;
        this.mapper =mapper;

        this.barnServiceUrl = "http://" + barnServiceHost + ":" + barnServicePort + "/barn/";
        this.horseServiceUrl = "http://" + horseServiceHost + ":" + horseServicePort + "/horse?barnId=";
        this.ownerServiceUrl = "http:.//" + ownerServiceHost + ":" + ownerServicePort + "/owner?barnId=";
    }

    @Override
    public Barn getBarn(int barnId) {
        try {
            String url = barnServiceUrl + barnId;
            LOG.debug("Will call getBarn API on URL: {} ", url);

            Barn barn = restTemplate.getForObject(url, Barn.class);
            LOG.debug("Found a Barn with id {} ", barn.getBarnId());

            return barn;
        } catch (HttpClientErrorException ex) {
            switch (ex.getStatusCode()) {
                case NOT_FOUND:
                    throw new NotFoundException(getErrorMessage(ex));
                case UNPROCESSABLE_ENTITY:
                    throw new InvalidInputException(getErrorMessage(ex));
                case GONE:
                    throw new NotFoundException(getErrorMessage(ex));
                default:
                    LOG.warn("Got an unexpected HTTP error: {}, will re-throw it", ex.getStatusCode());
                    LOG.warn("Error body: {}", ex.getResponseBodyAsString());
                    throw ex;
            }
        }

    }
    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ioex.getMessage();

        }
    }

    @Override
    public List<Horse> getHorses(int barnId) {
        try {
            String url = horseServiceUrl + barnId;
            LOG.debug("Will call getBarn API on URL: {} ", url);

            List<Horse> horses = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Horse>>() {
                    }).getBody();

            LOG.debug("Found {} horses for a Barn with id {} ", horses.size(), barnId);
            return horses;
        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting horses, return zero horses: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Owner> getOwners(int barnId) {
        try {
            String url = ownerServiceUrl + barnId;
            LOG.debug("Will call getBarn API on URL: {} ", url);

            List<Owner> owners = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Owner>>() {
                    }).getBody();

            LOG.debug("Found {} owners for a Barn with barnId {} ", owners.size(), barnId);
            return owners;
        } catch (Exception ex) {
            LOG.warn("Got an exception while requesting owners, return zero owners: {}", ex.getMessage());
            return new ArrayList<>();
        }
    }
}

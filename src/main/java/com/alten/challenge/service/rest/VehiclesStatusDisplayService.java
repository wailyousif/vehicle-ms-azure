package com.alten.challenge.service.rest;

import ch.qos.logback.classic.Logger;
import com.alten.challenge.db.repo.CustomersVehiclesRepo;
import com.alten.challenge.helper.Constants;
import com.alten.challenge.helper.Utils;
import com.alten.challenge.service.dto.VehiclesStatusDisplayRequest;
import com.alten.challenge.service.dto.VehiclesStatusDisplayResponse;
import com.alten.challenge.service.dto.VehiclesStatusList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

/**
 * Created by wailm.yousif on 4/5/19.
 */

@RestController
@RequestScope
@RequestMapping(path = "/display")
public class VehiclesStatusDisplayService {

    private static final Logger logger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CustomersVehiclesRepo customersVehiclesRepo;


    @CrossOrigin
    @RequestMapping(path = "/vehicles/status", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity<VehiclesStatusDisplayResponse> getVehiclesStatus(
            @RequestBody VehiclesStatusDisplayRequest vehiclesStatusDisplayRequest) {

        VehiclesStatusDisplayResponse vehiclesStatusDisplayResponse = new VehiclesStatusDisplayResponse();

        logger.info("vehiclesStatusDisplayRequest.connected=" + vehiclesStatusDisplayRequest.getConnected());

        try {

            List<VehiclesStatusList> vehiclesStatusLists = null;

            if (vehiclesStatusDisplayRequest.getConnected() == null) {
                vehiclesStatusLists = customersVehiclesRepo.getAllVehicles();
            }
            else {
                if (vehiclesStatusDisplayRequest.getConnected() == true) {
                    vehiclesStatusLists = customersVehiclesRepo.getConnectedVehicles(Utils.getCompareTime());
                }
                else {
                    vehiclesStatusLists = customersVehiclesRepo.getDisonnectedVehicles(Utils.getCompareTime());
                }
            }

            vehiclesStatusDisplayResponse.setSuccess(true);
            vehiclesStatusDisplayResponse.setResponseCode(0);
            vehiclesStatusDisplayResponse.setResponseMessage("Success");
            vehiclesStatusDisplayResponse.setVehiclesStatusList(vehiclesStatusLists);
        }
        catch (Exception e) {
            logger.error("Exception", e);
        }

        return new ResponseEntity<VehiclesStatusDisplayResponse>(vehiclesStatusDisplayResponse, HttpStatus.OK);
    }
}

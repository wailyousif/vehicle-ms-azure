package com.alten.challenge.service.rest;

import ch.qos.logback.classic.Logger;
import com.alten.challenge.db.entity.Vehicle;
import com.alten.challenge.db.repo.VehicleRepo;
import com.alten.challenge.service.dto.VehiclePingRequest;
import com.alten.challenge.service.dto.VehiclePingResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;
import java.util.Date;

/**
 * Created by wailm.yousif on 4/5/19.
 */

@RestController
@RequestScope
@RequestMapping(path = "/vehicle")
public class VehiclePingService {

    private static final Logger logger =
            (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private VehicleRepo vehicleRepo;


    @CrossOrigin
    @RequestMapping(path = "/ping", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody ResponseEntity<VehiclePingResponse> vehiclePing(@RequestBody VehiclePingRequest vehiclePingRequest) {

        VehiclePingResponse vehiclePingResponse = new VehiclePingResponse();

        try {
            logger.info("vehiclePingRequest.vin={}", vehiclePingRequest.getVin());
            Vehicle vehicle = vehicleRepo.findByVin(vehiclePingRequest.getVin());
            if (vehicle == null)
            {
                vehiclePingResponse.setSuccess(false);
                vehiclePingResponse.setResponseCode(-20);
                vehiclePingResponse.setResponseMessage("Invalid Vehicle");
                throw new Exception(vehiclePingResponse.getResponseMessage());
            }

            vehicle.setLastPing(new Date());
            vehicleRepo.save(vehicle);
            vehiclePingResponse.setSuccess(true);
            vehiclePingResponse.setResponseCode(0);
            vehiclePingResponse.setResponseMessage("Success");
        }
        catch (Exception e) {
            logger.error("Exception", e);
        }

        return new ResponseEntity<VehiclePingResponse>(vehiclePingResponse, HttpStatus.OK);
    }
}

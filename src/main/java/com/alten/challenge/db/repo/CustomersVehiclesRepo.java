package com.alten.challenge.db.repo;

import com.alten.challenge.db.entity.CustomersVehicles;
import com.alten.challenge.service.dto.VehiclesStatusList;
import org.hibernate.annotations.Filters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by wailm.yousif on 4/5/19.
 */

@Repository
public interface CustomersVehiclesRepo extends PagingAndSortingRepository<CustomersVehicles, Long> {

    @Query(value = "SELECT new com.alten.challenge.service.dto.VehiclesStatusList(" +
            "cv.vehicle.vin as vehicleVin, cv.customer.name as customerName, cv.customer.address as customerAddress, " +
            "cv.vehicle.lastPing as vehicleLastPing) " +
            "FROM CustomersVehicles cv")
    public List<VehiclesStatusList> getAllVehicles();


    @Query(value = "SELECT new com.alten.challenge.service.dto.VehiclesStatusList(" +
            "cv.vehicle.vin as vehicleVin, cv.customer.name as customerName, cv.customer.address as customerAddress, " +
            "cv.vehicle.lastPing as vehicleLastPing, true) " +
            "FROM CustomersVehicles cv WHERE " +
            "cv.vehicle.lastPing is not null and cv.vehicle.lastPing > :compareTime")
    public List<VehiclesStatusList> getConnectedVehicles(@Param("compareTime") Date compareTime);


    @Query(value = "SELECT new com.alten.challenge.service.dto.VehiclesStatusList(" +
            "cv.vehicle.vin as vehicleVin, cv.customer.name as customerName, cv.customer.address as customerAddress, " +
            "cv.vehicle.lastPing as vehicleLastPing, false) " +
            "FROM CustomersVehicles cv WHERE " +
            "cv.vehicle.lastPing is null or coalesce(cv.vehicle.lastPing,:compareTime) <= :compareTime")
    public List<VehiclesStatusList> getDisonnectedVehicles(@Param("compareTime") Date compareTime);

}

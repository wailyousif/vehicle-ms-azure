package com.alten.challenge.service.dto;

import com.alten.challenge.helper.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wailm.yousif on 4/5/19.
 */

public class VehiclesStatusList {

    private String vehicleVin;
    private String customerName;
    private String customerAddress;
    private Date vehicleLastPing;
    private Boolean vehicleIsConnected;


    public VehiclesStatusList() { }

    public VehiclesStatusList(String vehicleVin, String customerName, String customerAddress, Date vehicleLastPing) {
        this.vehicleVin = vehicleVin;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.vehicleLastPing = vehicleLastPing;
        this.vehicleIsConnected = Utils.vehicleIsConnected(vehicleLastPing);
    }

    public VehiclesStatusList(String vehicleVin, String customerName, String customerAddress, Date vehicleLastPing, Boolean vehicleIsConnected) {
        this.vehicleVin = vehicleVin;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.vehicleLastPing = vehicleLastPing;
        this.vehicleIsConnected = vehicleIsConnected;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getVehicleLastPing() {
        return vehicleLastPing;
    }

    public void setVehicleLastPing(Date vehicleLastPing) {
        this.vehicleLastPing = vehicleLastPing;
    }

    public Boolean getVehicleIsConnected() {
        return vehicleIsConnected;
    }

    public void setVehicleIsConnected(Boolean vehicleIsConnected) {
        this.vehicleIsConnected = vehicleIsConnected;
    }
}

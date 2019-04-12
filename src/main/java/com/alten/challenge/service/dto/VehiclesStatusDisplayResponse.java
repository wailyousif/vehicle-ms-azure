package com.alten.challenge.service.dto;

import java.util.List;

/**
 * Created by wailm.yousif on 4/6/19.
 */
public class VehiclesStatusDisplayResponse {

    private boolean success;
    private Integer responseCode;
    private String responseMessage;
    private List<VehiclesStatusList> vehiclesStatusList;

    public VehiclesStatusDisplayResponse() {
        this.success = false;
        this.responseCode = -10;
        this.responseMessage = "Generic Error";
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<VehiclesStatusList> getVehiclesStatusList() {
        return vehiclesStatusList;
    }

    public void setVehiclesStatusList(List<VehiclesStatusList> vehiclesStatusList) {
        this.vehiclesStatusList = vehiclesStatusList;
    }
}

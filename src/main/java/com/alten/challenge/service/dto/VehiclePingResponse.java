package com.alten.challenge.service.dto;

/**
 * Created by wailm.yousif on 3/6/17.
 */

public class VehiclePingResponse
{
    private boolean success;
    private Integer responseCode;
    private String responseMessage;

    public VehiclePingResponse() {
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

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

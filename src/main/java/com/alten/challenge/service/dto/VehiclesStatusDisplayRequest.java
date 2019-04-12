package com.alten.challenge.service.dto;

/**
 * Created by wailm.yousif on 4/5/19.
 */
public class VehiclesStatusDisplayRequest {

    private Boolean connected;

    public VehiclesStatusDisplayRequest() { }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }
}

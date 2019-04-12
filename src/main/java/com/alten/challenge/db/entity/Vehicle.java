package com.alten.challenge.db.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wailm.yousif on 4/5/19.
 */

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq")
    @SequenceGenerator(name="vehicle_seq", sequenceName = "vehicle_seq", allocationSize = 1, initialValue = 1)
    private long id;

    @Column(unique = true)
    private String vin;

    private Date lastPing;

    public Vehicle() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getLastPing() {
        return lastPing;
    }

    public void setLastPing(Date lastPing) {
        this.lastPing = lastPing;
    }
}

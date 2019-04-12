package com.alten.challenge.db.entity;

import javax.persistence.*;

/**
 * Created by wailm.yousif on 4/5/19.
 */

@Entity
public class CustomersVehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cv_seq")
    @SequenceGenerator(name="cv_seq", sequenceName = "cv_seq", allocationSize = 1, initialValue = 1)
    private long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Vehicle vehicle;

    private String registrationNo;

    public CustomersVehicles() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
}

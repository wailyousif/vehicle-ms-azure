package com.alten.challenge.db.entity;

import javax.persistence.*;

/**
 * Created by wailm.yousif on 4/5/19.
 */

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name="customer_seq", sequenceName = "customer_seq", allocationSize = 1, initialValue = 1)
    private long id;

    private String name;
    private String address;

    public Customer() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

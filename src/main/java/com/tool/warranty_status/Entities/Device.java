package com.tool.warranty_status.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="device")
public class Device {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="serialnumber")
    private long serialNumber;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;

    public Device(long id, long serialNumber, String brand, String model) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.brand = brand;
        this.model = model;
    }

    public Device() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

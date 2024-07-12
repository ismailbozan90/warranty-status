package com.tool.warranty_status.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="warranty")
public class Warranty {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="deviceid")
    private long deviceId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "deviceid")
    private List<Device> devices;

    @Column(name="purchasedate")
    private Date purchaseDate;

    @Column(name="warrantystatus")
    private int warrantyStatus;

    public Warranty(long id, List<Device> devices, long deviceId, Date purchaseDate, int warrantyStatus) {
        this.id = id;
        this.devices = devices;
        this.deviceId = deviceId;
        this.purchaseDate = purchaseDate;
        this.warrantyStatus = warrantyStatus;
    }

    public Warranty() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public int getWarrantyStatus() {
        return warrantyStatus;
    }

    public void setWarrantyStatus(int warrantyStatus) {
        this.warrantyStatus = warrantyStatus;
    }
}

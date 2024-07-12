package com.tool.warranty_status.Repository;

import com.tool.warranty_status.Entities.Device;
import com.tool.warranty_status.Entities.Warranty;

import java.util.List;

public interface IDataAccess {

    List<Device> getDeviceList();
    List<Warranty> warrantyList();

    void addDevice(Device device);
    void updateDevice(Device device);
    void deleteDevice(Device device);

    void checkWarranty();

}

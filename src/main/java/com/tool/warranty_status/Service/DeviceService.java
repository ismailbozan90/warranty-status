package com.tool.warranty_status.Service;

import com.tool.warranty_status.Entities.Warranty;
import com.tool.warranty_status.Repository.IDataAccess;
import com.tool.warranty_status.Entities.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService implements IDeviceService {

    IDataAccess iDataAccess;

    @Autowired
    public DeviceService(IDataAccess iDataAccess) {
        this.iDataAccess = iDataAccess;
    }

    @Override
    public List<Device> getDeviceList() {
        return iDataAccess.getDeviceList();
    }

    @Override
    public List<Warranty> warrantyList() {
        return iDataAccess.warrantyList();
    }

    @Override
    public void addDevice(Device device) {
        iDataAccess.addDevice(device);
    }

    @Override
    public void updateDevice(Device device) {
        iDataAccess.addDevice(device);
    }

    @Override
    public void deleteDevice(Device device) {
        iDataAccess.addDevice(device);
    }

    @Override
    public void checkWarranty() {
        iDataAccess.checkWarranty();
    }
}

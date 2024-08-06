package com.tool.warranty_status.Service;

import com.tool.warranty_status.Entities.Device;
import com.tool.warranty_status.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceService {

    DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository dataAccess) {
        this.deviceRepository = dataAccess;
    }

    public List<Device> getDeviceList() {
        return deviceRepository.getDeviceList();
    }

    public boolean addDevice(Device device) {
        return deviceRepository.addDevice(device);
    }

    public boolean updateDevice(Device device) {
        return deviceRepository.updateDevice(device);
    }

    public boolean deleteDevice(Device device) {
        return deviceRepository.deleteDevice(device);
    }
}

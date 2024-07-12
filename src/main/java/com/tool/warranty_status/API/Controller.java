package com.tool.warranty_status.API;

import com.tool.warranty_status.Entities.Device;
import com.tool.warranty_status.Entities.Warranty;
import com.tool.warranty_status.Service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    IDeviceService iDeviceService;

    @Autowired
    public Controller(IDeviceService iDeviceService) {
        this.iDeviceService = iDeviceService;
    }

    @GetMapping("devicelist")
    public List<Device> deviceList() {
        return iDeviceService.getDeviceList();
    }

    @GetMapping("warrantylist")
    public List<Warranty> warrantyList() {
        return iDeviceService.warrantyList();
    }


    @PostMapping("adddevice")
    public void addDevice(@RequestBody Device device) {
        iDeviceService.addDevice(device);
    }

    @PostMapping("updatedevice")
    public void updateDevice(@RequestBody Device device) {
        iDeviceService.updateDevice(device);
    }

    @PostMapping("deletedevice")
    public void deleteDevice(@RequestBody Device device) {
        iDeviceService.deleteDevice(device);
    }

    @GetMapping("checkWarranty")
    public void checkWarranty() {
        iDeviceService.checkWarranty();
    }

}

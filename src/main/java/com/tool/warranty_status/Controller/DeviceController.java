package com.tool.warranty_status.Controller;

import com.tool.warranty_status.Entities.Device;
import com.tool.warranty_status.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeviceController {

    DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("devices")
    public ResponseEntity<List<Device>> deviceList() {
        List<Device> list = deviceService.getDeviceList();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("devices")
    public ResponseEntity<Boolean> addDevice(@RequestBody Device device) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.addDevice(device));
    }

    @PutMapping("devices")
    public ResponseEntity<Boolean> updateDevice(@RequestBody Device device) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.updateDevice(device));
    }

    @DeleteMapping("devices/{id}")
    public ResponseEntity<Boolean> deleteDevice(@RequestBody Device device) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.deleteDevice(device));
    }

}

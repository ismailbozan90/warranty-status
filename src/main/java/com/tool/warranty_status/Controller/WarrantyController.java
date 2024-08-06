package com.tool.warranty_status.Controller;

import com.tool.warranty_status.Entities.Warranty;
import com.tool.warranty_status.Service.WarrantyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WarrantyController {

    WarrantyService warrantyService;

    @Autowired
    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    @GetMapping("warranty")
    public ResponseEntity<List<Warranty>> warrantyList() {
        List<Warranty> result = warrantyService.warrantyList();
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("checkWarranty")
    public ResponseEntity<Integer> checkWarranty() {
        int count = warrantyService.checkWarranty();
        if (count == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

}

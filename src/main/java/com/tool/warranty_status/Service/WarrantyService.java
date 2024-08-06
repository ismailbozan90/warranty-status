package com.tool.warranty_status.Service;

import com.tool.warranty_status.Entities.Warranty;
import com.tool.warranty_status.Repository.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarrantyService {

    WarrantyRepository warrantyRepository;

    @Autowired
    public WarrantyService(WarrantyRepository warrantyRepository) {
        this.warrantyRepository = warrantyRepository;
    }

    public List<Warranty> warrantyList() {
        return warrantyRepository.warrantyList();
    }

    public int checkWarranty() {
        return warrantyRepository.checkWarranty();
    }
}

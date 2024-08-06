package com.tool.warranty_status.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="warranty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Warranty {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="device_id")
    private long deviceId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "device_id")
    private List<Device> devices;

    @Column(name="purchase_date")
    private Date purchaseDate;

    @Column(name="warranty_status")
    private int warrantyStatus;
}

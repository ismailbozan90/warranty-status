package com.tool.warranty_status.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="device")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="serial_number")
    private long serialNumber;

    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;
}

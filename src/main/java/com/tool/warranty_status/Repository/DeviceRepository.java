package com.tool.warranty_status.Repository;

import com.tool.warranty_status.Entities.Device;
import com.tool.warranty_status.Entities.Warranty;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public class DeviceRepository {

    private final EntityManager entityManager;

    @Autowired
    public DeviceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Device> getDeviceList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Device", Device.class).getResultList();
    }

    public boolean addDevice(Device device) {
        Session session = entityManager.unwrap(Session.class);

        try {
            session.persist(device);

            Warranty warranty = new Warranty();
            warranty.setDeviceId(device.getId());
            warranty.setPurchaseDate(new Date());

            LocalDate dateNow = LocalDate.now();
            LocalDate warrantyDate = warranty.getPurchaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long monthsBetween = ChronoUnit.MONTHS.between(warrantyDate, dateNow);
            if (monthsBetween < 24) {
                warranty.setWarrantyStatus(1);
            }

            try {
                session.persist(warranty);
                return true;
            } catch (Exception e) {
                System.out.println("Error second query add device : " + e.getMessage());
                return false;
            }


        } catch (Exception e) {
            System.out.println("Error first query add device: " + e.getMessage());
            return false;
        }


    }

    public boolean updateDevice(Device device) {
        Session session = entityManager.unwrap(Session.class);
        try {
            session.merge(device);
            return true;
        } catch (Exception e) {
            System.out.println("Error updating device: " + e.getMessage());
            return false;
        }

    }

    public boolean deleteDevice(Device device) {
        Session session = entityManager.unwrap(Session.class);
        Device findToDelete = session.get(Device.class, device.getId());
        try {
            session.remove(findToDelete);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting device: " + e.getMessage());
            return false;
        }
    }
}

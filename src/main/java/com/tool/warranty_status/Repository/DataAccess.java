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
public class DataAccess implements IDataAccess {

    private EntityManager entityManager;

    @Autowired
    public DataAccess(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Device> getDeviceList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Device").getResultList();
    }

    @Override
    public List<Warranty> warrantyList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Warranty").getResultList();
    }

    @Override
    public void addDevice(Device device) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(device);

        Warranty warranty = new Warranty();
        warranty.setDeviceId(device.getId());
        warranty.setPurchaseDate(new Date());

        LocalDate dateNow = LocalDate.now();
        LocalDate warrantyDate = warranty.getPurchaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long monthsBetween = ChronoUnit.MONTHS.between(warrantyDate, dateNow);
        if (monthsBetween < 24) {
            warranty.setWarrantyStatus(1);
        }

        session.saveOrUpdate(warranty);
    }

    @Override
    public void updateDevice(Device device) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(device);
    }

    @Override
    public void deleteDevice(Device device) {
        Session session = entityManager.unwrap(Session.class);
        Device findToDelete = session.get(Device.class, device.getId());
        session.delete(device);
    }

    @Override
    public void checkWarranty() {
        Session session = entityManager.unwrap(Session.class);
        List<Warranty> list = session.createQuery("from Warranty").getResultList();
        LocalDate dateNow = LocalDate.now();
        for (Warranty warranty: list) {
            LocalDate warrantyDate = warranty.getPurchaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long monthsBetween = ChronoUnit.MONTHS.between(warrantyDate, dateNow);
            if (monthsBetween >= 24 && warranty.getWarrantyStatus() == 1) {
                warranty.setWarrantyStatus(0);
                session.saveOrUpdate(warranty);
            }
        }
    }
}

package com.tool.warranty_status.Repository;

import com.tool.warranty_status.Entities.Warranty;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
@Transactional
public class WarrantyRepository {

    private final EntityManager entityManager;

    @Autowired
    public WarrantyRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<Warranty> warrantyList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Warranty", Warranty.class).getResultList();
    }

    public int checkWarranty() {
        Session session = entityManager.unwrap(Session.class);
        List<Warranty> list = session.createQuery("from Warranty", Warranty.class).getResultList();
        LocalDate dateNow = LocalDate.now();
        AtomicInteger count = new AtomicInteger(0);
        list.stream()
                .filter(warranty -> {
                    LocalDate warrantyDate = warranty.getPurchaseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    long monthsBetween = ChronoUnit.MONTHS.between(warrantyDate, dateNow);
                    return monthsBetween >= 24 && warranty.getWarrantyStatus() == 1;
                })
                .forEach(warranty -> {
                    warranty.setWarrantyStatus(0);
                    session.merge(warranty);
                    count.getAndIncrement();
                });

        return count.get();
    }
}

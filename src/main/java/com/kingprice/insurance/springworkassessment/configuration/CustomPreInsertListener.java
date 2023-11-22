package com.kingprice.insurance.springworkassessment.configuration;

import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CustomPreInsertListener implements PreInsertEventListener {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
    }


    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        Object entity = event.getEntity();

        // Check if the entity has an ID and is not yet managed
        if (event.getEntity() instanceof BaseEntity &&
            ((BaseEntity) event.getEntity()).getId() != null &&
            (!event.getSession().contains(entity) || !event.getSession().isDirty())
        ) {
            // Explicitly insert the entity into the database
            event.getSession().persist(entity);
            return false; // Signal that the default insert should be skipped
        }
        return true; // Continue with the default insert
    }
}

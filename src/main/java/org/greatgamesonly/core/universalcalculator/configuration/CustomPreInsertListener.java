package org.greatgamesonly.core.universalcalculator.configuration;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomPreInsertListener implements PreInsertEventListener
{

    Logger logger = LoggerFactory.getLogger(CustomPreInsertListener.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {

        if(((BaseEntity)event.getEntity()).getId() != null || isDirty(event)) {
            logger.info("merge back detached entity");
            event.getSession().merge(event.getEntity());
            event.getSession().update(event.getEntity());
            event.getSession().persist(event.getEntity());
            return false;
        }

        return true;
    }

    private boolean isDirty(PreInsertEvent event) {
        try {
            return event.getSession().isDirty();
        } catch(Exception ignored) {
            return true;
        }
    }
}

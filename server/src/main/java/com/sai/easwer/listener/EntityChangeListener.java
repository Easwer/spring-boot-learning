package com.sai.easwer.listener;

import com.sai.easwer.entity.BaseEntity;
import com.sai.easwer.security.CurrentSessionDetails;
import java.util.Calendar;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.stereotype.Component;

/**
 * This class will listen all entity updates and persist.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:06:05
 */
@Component
public class EntityChangeListener {

    /**
     * Fills created_time and updated_time with current time,
     * create_by and updated_by with current login user and
     * generate random {@link java.util.UUID} if id is null.
     * 
     * @param entity Instance of the entity. {@link BaseEntity}
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setVersion(1);
        entity.setLastUpdatedTime(Calendar.getInstance().getTimeInMillis());
        entity.setCreatedTime(Calendar.getInstance().getTimeInMillis());
        entity.setCreatedBy(CurrentSessionDetails.getUsername());
        entity.setLastUpdatedBy(CurrentSessionDetails.getUsername());
    }

    /**
     * Fills updated_time with current time and updated_by with current login user.
     * 
     * @param entity Instance of the entity. {@link BaseEntity}
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setVersion(entity.getVersion() + 1);
        entity.setLastUpdatedTime(Calendar.getInstance().getTimeInMillis());
        entity.setLastUpdatedBy(CurrentSessionDetails.getUsername());
    }

}

package com.sai.easwer.listener;

import java.util.Calendar;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.sai.easwer.entity.BaseEntity;
import com.sai.easwer.security.CurrentSessionDetails;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-17 12:51:29
 * @desc This class will listen all entity updates and persist.
 */
public class EntityChangeListener {

    /**
     * 
     * @param entity
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
     * 
     * @param entity
     */
    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setVersion(entity.getVersion() + 1);
        entity.setLastUpdatedTime(Calendar.getInstance().getTimeInMillis());
        entity.setLastUpdatedBy(CurrentSessionDetails.getUsername());
    }

}
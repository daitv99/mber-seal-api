package com.smart.service.impl;

import com.smart.entity.SealNotification;
import com.smart.service.SealNotificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SealNotificationServiceImpl extends BaseServiceImpl<SealNotification, Long> implements SealNotificationService {

    public SealNotificationServiceImpl(JpaRepository<SealNotification, Long> repository) {
        super(repository);
    }
}

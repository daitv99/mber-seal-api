package com.smart.service.impl;

import com.smart.entity.SealRequest;
import com.smart.service.SealRequestService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SealRequestServiceImpl extends BaseServiceImpl<SealRequest, Long> implements SealRequestService {

    public SealRequestServiceImpl(JpaRepository<SealRequest, Long> repository) {
        super(repository);
    }
}

package com.smart.service.impl;

import com.smart.entity.Seal;
import com.smart.service.SealService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class SealServiceImpl extends BaseServiceImpl<Seal, Long> implements SealService {

    public SealServiceImpl(JpaRepository<Seal, Long> repository) {
        super(repository);
    }
}

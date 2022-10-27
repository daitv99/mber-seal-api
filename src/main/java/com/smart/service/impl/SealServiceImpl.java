package com.smart.service.impl;

import com.smart.dto.search.SearchDto;
import com.smart.entity.Seal;
import com.smart.repository.SealRepository;
import com.smart.service.SealService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SealServiceImpl extends BaseServiceImpl<Seal, Long> implements SealService {

    private final SealRepository repository;

    public SealServiceImpl(SealRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Page<Seal> page(SearchDto searchDto) {
        return null;
    }
}

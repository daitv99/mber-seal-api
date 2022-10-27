package com.smart.service;

import com.smart.dto.search.SearchDto;
import org.springframework.data.domain.Page;

public interface BaseSearchService<T> {

    <S extends SearchDto> Page<T> page(S s);
}

package com.smart.service;

import com.smart.dto.search.SearchDto;
import com.smart.entity.Seal;

public interface SealService extends BaseService<Seal, Long>, BaseSearchService<Seal, SearchDto> {

}

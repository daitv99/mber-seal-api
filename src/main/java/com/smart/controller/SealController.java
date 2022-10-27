package com.smart.controller;

import com.smart.dto.handler.ResponseHandler;
import com.smart.dto.search.SearchDto;
import com.smart.entity.Seal;
import com.smart.service.SealService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Log4j
@RestController
@RequestMapping("/seal-manages")
@Api(value = "seal", description = "Con dấu")
public class SealController extends BaseController<Seal, Long> implements BaseSearchController<SearchDto> {

    private final SealService sealService;

    @Autowired
    protected SealController(SealService sealService) {
        super(sealService);
        this.sealService = sealService;
    }

    public ResponseEntity<Object> getPage(@Valid @RequestBody SearchDto searchDto) {
        return ResponseHandler.generateResponse(HttpStatus.OK, "", sealService.page(searchDto));
    }
}

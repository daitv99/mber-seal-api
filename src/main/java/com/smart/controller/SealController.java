package com.smart.controller;


import com.smart.entity.Seal;
import com.smart.service.SealService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Log4j
@RestController
@RequestMapping("/seal")
@Api(value = "seal", description = "Con dấu")
public class SealController extends BaseController<Seal, Long> {

    private final SealService sealService;

    @Autowired
    protected SealController(SealService sealService) {
        super(sealService);
        this.sealService = sealService;
    }
}

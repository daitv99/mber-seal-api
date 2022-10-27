package com.smart.controller;

import com.smart.entity.SealRequest;
import com.smart.service.SealRequestService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Log4j
@RestController
@RequestMapping("/seal-request")
@Api(value = "seal-request", description = "Yêu cầu con dấu")
public class SealRequestController extends BaseController<SealRequest, Long>{

   private final SealRequestService sealRequestService;

    @Autowired
    public SealRequestController(SealRequestService sealRequestService) {
        super(sealRequestService);
        this.sealRequestService = sealRequestService;
    }
}

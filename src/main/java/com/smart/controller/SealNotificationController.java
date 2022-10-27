package com.smart.controller;

import com.smart.entity.SealNotification;
import com.smart.service.BaseService;
import com.smart.service.SealNotificationService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Log4j
@RestController
@RequestMapping("/seal-notification")
@Api(value = "seal-notification", description = "Thông báo nhận con dấu")
public class SealNotificationController extends BaseController<SealNotification, Long> {

    private final SealNotificationService sealNotificationService;

    @Autowired
    public SealNotificationController(BaseService<SealNotification, Long> baseService, SealNotificationService sealNotificationService) {
        super(baseService);
        this.sealNotificationService = sealNotificationService;
    }
}

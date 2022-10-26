package com.smart.controller;

import com.smart.service.excel.IExportExcel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;

public abstract class IEExcelBaseController {

    protected ResponseEntity<InputStreamResource> exportExcel(IExportExcel export) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(export.run());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=%s", export.getFileName()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}

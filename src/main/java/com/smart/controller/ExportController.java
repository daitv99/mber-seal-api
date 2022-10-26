package com.smart.controller;

import com.smart.dto.search.ReportSearchDto;
import com.smart.dto.handler.ResponseHandler;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/exports")
@RequiredArgsConstructor
@Api(value = "exports", description = "Export excel")
public class ExportController extends IEExcelBaseController{

//    private final LetterToService letterToService;
//    private final LetterFromService letterFromService;
//
//    @PostMapping("/letter-to-report")
//    public ResponseEntity<InputStreamResource> getLetterFromReport(@Valid @RequestBody ReportSearchDto searchDto) throws Exception {
//        return exportExcel(new ExLetterToReport(letterToService.getLetterReportList(searchDto), searchDto));
//    }
//
//    @PostMapping("/letter-from-report")
//    public ResponseEntity<InputStreamResource> getLetterToReport(@Valid @RequestBody ReportSearchDto searchDto) throws Exception {
//        return exportExcel(new ExLetterFromReport(letterFromService.getLetterReportList(searchDto), searchDto));
//
//    }
//
//    @PostMapping("/letter-from-cost-report")
//    public ResponseEntity<InputStreamResource> getLetterFromCostReport(@Valid @RequestBody ReportSearchDto searchDto) throws Exception {
//        return exportExcel(new ExLetterFromCostReport(letterFromService.getLetterCostReportList(searchDto), searchDto));
//    }
//
//    @PostMapping("/letter-synthesis-report")
//    public HttpEntity<?> getLetterSynthesisReport(@Valid @RequestBody ReportSearchDto searchDto) throws Exception {
//        if (searchDto.getIsLetterTo() == null) {
//            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Chưa chọn loại thư", null);
//        }
//
//        if (searchDto.getYear() == null) {
//            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Chưa chọn năm", null);
//        }
//
//        if (!searchDto.getIsLetterTo()) {
//            return exportExcel(new ExLetterSynthesisReport(letterFromService.getLetterSynthesisReport(searchDto), searchDto));
//        } else {
//            return exportExcel(new ExLetterSynthesisReport(letterToService.getLetterSynthesisReport(searchDto), searchDto));
//        }
//    }
}

package com.smart.service;

import com.smart.dto.report.BaseReport;
import com.smart.dto.search.ReportSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BaseReportService {

    <R extends BaseReport> Page<R> getLetterReport(ReportSearchDto s);

    default <R extends BaseReport> Page<R> getLetterCostReport(ReportSearchDto s) {
        return new PageImpl<>(new ArrayList<>());
    }

    default Map<String, Integer> getLetterSynthesisReport(ReportSearchDto s) {
        return new HashMap<>();
    }

    <R extends BaseReport> List<R> getLetterReportList(ReportSearchDto s);

    default <R extends BaseReport> List<R> getLetterCostReportList(ReportSearchDto s) {
        return new ArrayList<>();
    }
}

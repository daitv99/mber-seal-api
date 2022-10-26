package com.smart.dto.search;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReportSearchDto extends SearchDto {

    private Long organizationId; //Cơ quan
    private String organizationName; //Cơ quan
    private Long unitId; //Đơn vị
    private String unitName; //Đơn vị
    private Long deliveryUnitId; //Đơn vị chuyển phát
    private String deliveryUnitName; //Đơn vị chuyển phát
    private Long letterCodeId; //Sổ thư đến
    private String letterCodeName; //Sổ thư đến
    private Long urgencyLevelId; //Độ khẩn
    private String urgencyLevelName; //Độ khẩn
    private Long securityLevelId; //Độ mật
    private String securityLevelName; //Độ mật
    private Date fromDate; //Từ ngày
    private Date toDate; //Đến ngày
    private Integer type; //Phân loại thư;
    private Integer status; //Trạng thái
    private Integer year; //Trạng thái
    private Boolean isLetterTo; //Là thư đến
}

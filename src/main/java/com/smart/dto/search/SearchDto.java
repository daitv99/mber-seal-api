package com.smart.dto.search;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class SearchDto {

    private int pageIndex;
    private int pageSize;
    private Long id;
    private String keyword; //Tìm kiếm chung
    private Boolean isDeleted = false;

    public int getOrDefaultPageIndex() {
        return pageIndex <= 0 ? 0 : pageIndex - 1;
    }

    public int getOrDefaultPageSize() {
        return pageSize == 0 ? 10 : pageSize;
    }
}

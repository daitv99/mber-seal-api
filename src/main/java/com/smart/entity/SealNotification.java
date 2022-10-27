package com.smart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.component.util.DateUtils;
import com.smart.config.SetToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/*
*  Thông báo con dấu
* */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_seal_notification")
public class SealNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "place")
    private String place; //Địa điểm

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date startTime; //Thời gian bắt đầu

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date endTime; //Thời gian kết thúc

    @Column(name = "seal_request_ids")
    @Convert(converter = SetToStringConverter.class)
    private Set<String> sealRequestIds; //Danh sách yêu cầu - Lấy đơn vị yêu cầu

    @Column(name = "org_ids")
    @Convert(converter = SetToStringConverter.class)
    private Set<String> orgIds; //Danh sách đơn vị

    @Column(name = "emp_ids")
    @Convert(converter = SetToStringConverter.class)
    private Set<String> empIds; //Danh sách cá nhân nhận

    @Column(name = "seal_ids")
    @Convert(converter = SetToStringConverter.class)
    private Set<String> sealIds; //Các con dấu liên quan

    @Column(name = "status")
    private Integer status = 1; //Trạng thái con dấu: 1.Chưa bàn giao/2.Đã bàn giao, chưa sử dụng/3.Đang sử dụng/4.Đã thu hồi/5.Đã hủy/6.Tạm dừng sử dụng. mặc định là Chưa bàn giao cho phép người dùng chọn

    /*
    * Thiếu thông tin đăng ký, thêm sau*/

}

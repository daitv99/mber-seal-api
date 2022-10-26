package com.smart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.component.constrant.EFieldMatch;
import com.smart.component.constrant.FieldMatch;
import com.smart.component.util.DateUtils;
import com.smart.config.SetToStringConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/*Yêu cầu con dấu*/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_seal_request")
@FieldMatch.List({
        @FieldMatch(field = "rejectReason", fieldMatch = "status", matchBy = EFieldMatch.REQUIRED_IF, matchValue = "3",
                message = "'rejectReason' Lý do từ chối bắt buộc nhập "),
        @FieldMatch(field = "sealId", fieldMatch = "type", matchBy = EFieldMatch.REQUIRED_IF, matchValue = "2,3,4",
                message = "'sealId' Con dấu bắt buộc nhập")
})
public class SealRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seal_id")
    private Long sealId;

    @ManyToOne
    @JoinColumn(name = "seal_id", insertable = false, updatable = false)
    @ApiModelProperty(hidden = true)
    private Seal seal; //Con dấu

    /*
    1.	Yêu cầu cấp mới con dấu
    2.	Yêu cầu cấp phát con dấu do thay đổi thông tin liên quan đến con dấu
    3.	Yêu cầu thu hồi con dấu do tạm dừng sử dụng
    4.	Yêu cầu thu hồi con dấu do hết hiệu lực
    */
    @Column(name = "type", nullable = false)
    @NotNull(message = "Loại yêu cầu bắt buộc nhập")
    private Integer type; //Loại yêu cầu

    @Column(name = "status")
    private int status = 1; //Trạng thái: 1.Chờ xử lý, 2.Xác nhận, 3.Từ chối

    @Column(name = "reason", length = 2000)
    @Size(max = 2000, message = "Vượt quá số ký tự cho phép")
    private String reason; //Lý do

    @Column(name = "reject_reason", length = 2000)
    @Size(max = 2000, message = "Vượt quá số ký tự cho phép")
    private String rejectReason; //Lý do

    @Column(name = "org_request_id", nullable = false)
    @NotNull(message = "Đơn vị yêu cầu bắt buộc nhập")
    private Long orgRequestId; //Đơn vị yêu cầu

    @ManyToOne
    @JoinColumn(name = "org_request_id", insertable = false, updatable = false)
    @ApiModelProperty(hidden = true)
    private Organization orgRequest;

    @Column(name = "promulgate_date_from")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date promulgateDateFrom; // Ngay ban hanh tu

    @Column(name = "promulgate_date_to")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date promulgateDateTo; // Ngay ban hanh den

    @Column(name = "employee_id")
    private Long employeeId; // Người bàn giao

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    @ApiModelProperty(hidden = true)
    private Employee employee; // Người bàn giao
//
//    @Column(name = "org_assign_id")
//    private Long orgAssignId; // Đơn vị bàn giao
//
//    @ManyToOne
//    @JoinColumn(name = "org_assign_id", insertable = false, updatable = false)
//    @ApiModelProperty(hidden = true)
//    private Organization orgAssign; // Đơn vị bàn giao

    @Column(name = "file_attachment_ids")
    @NotNull(message = "File đính kèm bắt buộc nhập")
    @Convert(converter = SetToStringConverter.class)
    private List<String> fileAttachmentIds;

    @Transient
    private List<FileAttachment> fileAttachments;
}

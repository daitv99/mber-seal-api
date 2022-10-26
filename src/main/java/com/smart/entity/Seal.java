package com.smart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.component.util.DateUtils;
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

/*
* Con dấu
* */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_seal")
public class Seal extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 250)
    @NotNull(message = "Tên con dấu bắt buộc nhập")
    @Size(max = 250, message = "Vượt quá số ký tự cho phép")
    private String name; //Tên con dấu

    @Column(name = "code", nullable = false, unique = true)
    @NotNull(message = "ID con dấu bắt buộc nhập")
    private String code; //ID con dấu: Tự động hiển thị mã đơn vị _số thứ tự theo đơn vị quản lý con dấu. Mã này không được trùng trong cùng đơn vị quản lý.

    @Column(name = "type", nullable = false)
    @NotNull(message = "Loại con dấu bắt buộc nhập")
    private Integer type; //Loại con dấu: 1.Thường, 2.Điện tử

    @Column(name = "description", length = 500)
    @Size(max = 500, message = "Vượt quá số ký tự cho phép")
    private String description; //Mô tả

    @Column(name = "area", nullable = false)
    private Integer area; //Vùng: Cho chọn vùng là: 1.Khu vực phía Bắc/2.Khu vực Hà Nội/3.Khu vực Miền Trung/4.Khu vực Tp Hồ Chí Minh/5.Khu vực phía Nam. Mặc định là chọn. Bắt buộc chọn 1 vùng

    @Column(name = "published_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date publishedDate; //Ngày hiệu lực con dấu

    @Column(name = "org_owner_id", nullable = false)
    @NotNull(message = "Đơn vị cấp bắt buộc nhập")
    private Long orgOwnerId; //Đơn vị cấp: Bắt buộc chọn từ danh sách: MB->  Ngân hàng TMCP Quân đội, Lào, Campuchia, Nga.

    @ManyToOne
    @JoinColumn(name = "org_owner_id", insertable = false, updatable = false)
    @ApiModelProperty(hidden = true)
    private Organization orgOwner; //Đơn vị cấp

    @Column(name = "org_manage_id")
    private Long orgManageId; //Đơn vị quản lý: Không bắt buộc chọn, nếu đơn vị quản lý khác đơn vị sử dụng thì cần chọn đơn vị quản lý, chỉ chọn được các đơn vị từ đơn vị user có vai trò lãnh đạo/thủ trưởng/quản lý con dấu trở xuống, nếu không nhập thì mặc định lưu đơn vị quản lý là đơn vị sử dụng.

    @ManyToOne
    @JoinColumn(name = "org_manage_id", insertable = false, updatable = false)
    @ApiModelProperty(hidden = true)
    private Organization orgManage; //Đơn vị quản lý

    @Column(name = "org_assign_id")
    @NotNull(message = "Đơn vị sử dụng bắt buộc nhập")
    private Long orgAssignId; //Đơn vị sử dụng: Bắt buộc chọn 1 đơn vị sử dụng. chỉ chọn được các đơn vị từ đơn vị user có vai trò lãnh đạo/thủ trưởng/quản lý con dấu trở xuống.

    @ManyToOne
    @JoinColumn(name = "org_assign_id", insertable = false, updatable = false)
    @ApiModelProperty(hidden = true)
    private Organization orgAssign; //Đơn vị sử dụng

    @Column(name = "status")
    private Integer status = 1; //Trạng thái: 1.Chưa bàn giao/2.Đã bàn giao, chưa sử dụng/3.Đang sử dụng/4.Đã thu hồi/5.Đã hủy/6.Tạm dừng sử dụng. mặc định là Chưa bàn giao cho phép người dùng chọn

    @Column(name = "image_path")
    private String imagePath; //path ảnh

    @Column(name = "attachment_path")
    private String attachmentPath; // path file đính kèm

    @ManyToMany
    @JoinTable(name = "s_seal_protocol",
            joinColumns = @JoinColumn(name = "seal_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "document_id")
    )
    private List<Document> listProtocol; //Danh sách tờ trình

    @ManyToMany
    @JoinTable(name = "s_seal_report",
            joinColumns = @JoinColumn(name = "seal_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id", referencedColumnName = "document_id")
    )
    private List<Document> listReport; //Danh sách biên bản
}

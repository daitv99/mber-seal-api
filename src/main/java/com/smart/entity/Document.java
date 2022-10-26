package com.smart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smart.component.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_document")
public class Document extends BaseEntity {

    @Id
    @Column(name = "document_id")
    private Long documentId; // Id van ban

    @Column(name = "code")
    private String code; // Ma hieu van ban

    @Column(name = "title")
    private String title; // Tieu de

    @Column(name = "content")
    private String content; // Noi dung trich yeu

    @Column(name = "sumary")
    private String summary; // Tom tat noi dung van ban;

    @Column(name = "signer")
    private String signer; // Nguoi ky

    @Column(name = "sys_organization_id")
    private Long sysOrganizationId; // ID don vi

    @Column(name = "org_name")
    private String orgName; // Don vi ban hanh

    @Column(name = "promulgate_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date promulgateDate; // Ngay ban hanh

    @Column(name = "promulgate_date_from")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date promulgateDateFrom; // Ngay ban hanh tu

    @Column(name = "promulgate_date_to")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    private Date promulgateDateTo; // Ngay ban hanh den
}

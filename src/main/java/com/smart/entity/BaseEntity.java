package com.smart.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.component.util.DateUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Transient
    protected Map<String, String> attributes = new HashMap<>();

    @Column(name = "is_deleted")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private boolean isDeleted = false;

    @Column(name = "created_by")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Long createdBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_by")
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Long updatedBy;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.FORMAT_DATE_TIME, timezone = DateUtils.TIME_ZONE)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Date updatedAt;

    public Map<String, String> getAttributes() {
        setAttributes();
        return attributes;
    }

    public void setAttributes() {
    }
}

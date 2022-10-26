package com.smart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "s_seal_process")
public class SealProcess extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seal_id", nullable = false)
    @NotNull(message = "Id con dấu không được trống")
    private Long sealId;

    @Column(name = "seal_request_id", nullable = false)
    @NotNull(message = "Id yêu cầu không được trống")
    private Long sealRequestId;

    @Column(name = "status")
    private Long status;

}

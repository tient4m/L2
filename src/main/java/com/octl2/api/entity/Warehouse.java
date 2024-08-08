package com.octl2.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "bp_warehouse")
public class Warehouse {
    @Id
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "ffm_id")
    private Long fulfilmentId;
}

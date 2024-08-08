package com.octl2.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cf_default_delivery")
@Getter
@Setter
public class Delivery {
    @Id
    @Column(name = "cf_default_do_id")
    private Long deliveryId;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "ffm_id")
    private Long fulfilmentId;

    @Column(name = "lastmile_id")
    private Long lastmileId;

    @Column(name = "warehouse_id")
    private Long warehouseId;
}

package com.octl2.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bp_partner")
@Getter
@Setter
public class Partner {
    @Id
    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "partner_type")
    private Long partnerType;
}

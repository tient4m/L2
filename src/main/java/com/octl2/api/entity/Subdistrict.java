package com.octl2.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lc_subdistrict")
@Getter
@Setter
public class Subdistrict {
    @Id
    @Column(name = "subdistrict_id")
    private Long id;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortname;

    @Column(name = "code")
    private String code;

    @Column(name = "dcsr")
    private String description;
}

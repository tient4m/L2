package com.octl2.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lc_district")
@Getter
@Setter
public class District {
    @Id
    @Column(name = "district_id")
    private Long id;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortname;

    @Column(name = "code")
    private String code;

    @Column(name = "dcsr")
    private String description;
}

package com.octl2.api.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageConst {
    public static final String UPDATE_SUCCESS = "Update Success!";
    public static final String FIELD_PROVINCE_ID = "Province Id";
    public static final String PROVINCE_NOT_FOUND = "Province not found.";
    public static final String FIELD_DISTRICT_ID = "District Id";
    public static final String DISTRICT_NOT_FOUND = "District not found.";
    public static final String FIELD_SUBDISTRICT_ID = "Subdistrict Id";
    public static final String SUBDISTRICT_NOT_FOUND = "Subdistrict not found.";
    public static final String FIELD_FULFILMENT_ID = "Fulfilment Id";
    public static final String FULFILMENT_NOT_FOUND = "Fulfilment  not found.";
    public static final String FIELD_LASTMILE_ID = "Lastmile Id";
    public static final String LASTMILE_NOT_FOUND = "Lastmile  not found.";
    public static final String FIELD_WAREHOUSE_ID = "Warehouse Id";
    public static final String WAREHOUSE_NOT_FOUND = "Warehouse not found.";
    public static final String UPDATE_ERROR_BY_LEVEL = "The location must be greater than level mapping";

}

package com.octl2.api.repository;

import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.entity.Subdistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface SubdistrictRepository extends JpaRepository<Subdistrict, Long> {
    //boolean existsById(@NotNull Long id);

    @Query(value = "SELECT" +
            "   sbd.subdistrict_id AS locationId" +
            "   ,sbd.name" +
            "   ,sbd.shortname" +
            "   ,sbd.code" +
            "   ,sbd.dcsr AS description" +
            "   ,deli.ffm_id AS fulfilmentId" +
            "   ,ffm.name AS fulfilmentName" +
            "   ,ffm.shortname AS fulfilmentShortname" +
            "   ,deli.lastmile_id AS lastmileId" +
            "   ,lm.name AS lastmileName" +
            "   ,lm.shortname AS lastmileShortname" +
            "   ,deli.warehouse_id AS warehouseId" +
            "   ,wh.warehouse_name AS warehouseName" +
            "   ,wh.warehouse_shortname AS warehouseShortname" +
            "   FROM lc_subdistrict AS sbd" +
            "   LEFT JOIN" +
            "   lc_district AS dtr" +
            "   ON" +
            "   dtr.district_id = sbd.district_id" +
            "   LEFT JOIN" +
            "   lc_province AS prv" +
            "   ON" +
            "   prv.province_id = dtr.province_id" +
            "   LEFT JOIN" +
            "   cf_default_delivery AS deli" +
            "   ON" +
            "   deli.location_id = prv.province_id" +
            "   LEFT JOIN" +
            "   bp_partner AS ffm" +
            "   ON" +
            "   ffm.partner_id = deli.ffm_id" +
            "   LEFT JOIN" +
            "   bp_partner AS lm" +
            "   ON" +
            "   lm.partner_id = deli.lastmile_id" +
            "   LEFT JOIN" +
            "   bp_warehouse AS wh" +
            "   ON" +
            "   wh.warehouse_id = deli.warehouse_id" +
            "   WHERE" +
            "   sbd.district_id = :districtId" +
            "   ORDER BY" +
            "   sbd.subdistrict_id", nativeQuery = true)
    Set<LocationResultSet> getByLevel1(@Param("districtId") long districtId);

    @Query(value = "SELECT" +
            "   sbd.subdistrict_id AS locationId" +
            "   ,sbd.name" +
            "   ,sbd.shortname" +
            "   ,sbd.code" +
            "   ,sbd.dcsr AS description" +
            "   ,deli.ffm_id AS fulfilmentId" +
            "   ,ffm.name AS fulfilmentName" +
            "   ,ffm.shortname AS fulfilmentShortname" +
            "   ,deli.lastmile_id AS lastmileId" +
            "   ,lm.name AS lastmileName" +
            "   ,lm.shortname AS lastmileShortname" +
            "   ,deli.warehouse_id AS warehouseId" +
            "   ,wh.warehouse_name AS warehouseName" +
            "   ,wh.warehouse_shortname AS warehouseShortname" +
            "   FROM lc_subdistrict AS sbd" +
            "   LEFT JOIN" +
            "   lc_district AS dtr" +
            "   ON" +
            "   dtr.district_id = sbd.district_id" +
            "   LEFT JOIN" +
            "   cf_default_delivery AS deli" +
            "   ON" +
            "   deli.location_id = dtr.district_id" +
            "   LEFT JOIN" +
            "   bp_partner AS ffm" +
            "   ON" +
            "   ffm.partner_id = deli.ffm_id" +
            "   LEFT JOIN" +
            "   bp_partner AS lm" +
            "   ON" +
            "   lm.partner_id = deli.lastmile_id" +
            "   LEFT JOIN" +
            "   bp_warehouse AS wh" +
            "   ON" +
            "   wh.warehouse_id = deli.warehouse_id" +
            "   WHERE" +
            "   sbd.district_id = :districtId" +
            "   ORDER BY" +
            "   sbd.subdistrict_id", nativeQuery = true)
    Set<LocationResultSet> getByLevel2(@Param("districtId") long districtId);

    @Query(value = "SELECT" +
            "   sbd.subdistrict_id AS locationId" +
            "   ,sbd.name" +
            "   ,sbd.shortname" +
            "   ,sbd.code" +
            "   ,sbd.dcsr AS description" +
            "   ,deli.ffm_id AS fulfilmentId" +
            "   ,ffm.name AS fulfilmentName" +
            "   ,ffm.shortname AS fulfilmentShortname" +
            "   ,deli.lastmile_id AS lastmileId" +
            "   ,lm.name AS lastmileName" +
            "   ,lm.shortname AS lastmileShortname" +
            "   ,deli.warehouse_id AS warehouseId" +
            "   ,wh.warehouse_name AS warehouseName" +
            "   ,wh.warehouse_shortname AS warehouseShortname" +
            "   FROM lc_subdistrict AS sbd" +
            "   LEFT JOIN" +
            "   cf_default_delivery AS deli" +
            "   ON" +
            "   deli.location_id = sbd.subdistrict_id" +
            "   LEFT JOIN" +
            "   bp_partner AS ffm" +
            "   ON" +
            "   ffm.partner_id = deli.ffm_id" +
            "   LEFT JOIN" +
            "   bp_partner AS lm" +
            "   ON" +
            "   lm.partner_id = deli.lastmile_id" +
            "   LEFT JOIN" +
            "   bp_warehouse AS wh" +
            "   ON" +
            "   wh.warehouse_id = deli.warehouse_id" +
            "   WHERE" +
            "   sbd.district_id = :districtId" +
            "   ORDER BY" +
            "   sbd.subdistrict_id", nativeQuery = true)
    Set<LocationResultSet> getByLevel3(@Param("districtId") long districtId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE" +
            "   cf_default_delivery" +
            "   SET" +
            "   ffm_id = :#{#deliveryDTO.fulfilmentId}" +
            "   ,lastmile_id = :#{#deliveryDTO.lastmileId}" +
            "   ,warehouse_id = :#{#deliveryDTO.warehouseId}" +
            "   WHERE" +
            "   location_id = :subdistrictId", nativeQuery = true)
    void updateByLevel3(@Param("subdistrictId") long subdistrictId, @Param("deliveryDTO") DeliveryDTO deliveryDTO);
}

package com.octl2.api.repository;

import com.octl2.api.dto.DeliveryDTO;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.entity.Province;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

    //boolean existsById(@NotNull Long id);

    @Query(value = "SELECT" +
            "   prv.province_id AS locationId" +
            "   ,prv.name" +
            "   ,prv.code" +
            "   ,deli.ffm_id AS fulfilmentId" +
            "   ,ffm.name AS fulfilmentName" +
            "   ,ffm.shortname AS fulfilmentShortname" +
            "   ,deli.lastmile_id AS lastmileId" +
            "   ,lm.name AS lastmileName" +
            "   ,lm.shortname AS lastmileShortname" +
            "   ,deli.warehouse_id AS warehouseId" +
            "   ,wh.warehouse_name AS warehouseName" +
            "   ,wh.warehouse_shortname AS warehouseShortname" +
            "   FROM" +
            "   lc_province AS prv" +
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
            "   ORDER BY" +
            "   prv.province_id", nativeQuery = true)
    Set<LocationResultSet> getLByLevel1();

    @Query(value = "SELECT" +
            "   prv.province_id AS locationId" +
            "   ,prv.name" +
            "   ,prv.code" +
            "   ,dtr.district_id AS districtId" +
            "   ,dtr.name AS districtName" +
            "   ,dtr.code AS districtCode" +
            "   ,deli.ffm_id AS fulfilmentId" +
            "   ,ffm.name AS fulfilmentName" +
            "   ,ffm.shortname AS fulfilmentShortname" +
            "   ,deli.lastmile_id AS lastmileId" +
            "   ,lm.name AS lastmileName" +
            "   ,lm.shortname AS lastmileShortname" +
            "   ,deli.warehouse_id AS warehouseId" +
            "   ,wh.warehouse_name AS warehouseName" +
            "   ,wh.warehouse_shortname AS warehouseShortname" +
            "   FROM" +
            "   lc_province AS prv" +
            "   LEFT JOIN" +
            "   lc_district AS dtr" +
            "   ON" +
            "   dtr.province_id = prv.province_id" +
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
            "   ORDER BY" +
            "   dtr.district_id", nativeQuery = true)
    Set<LocationResultSet> getByLevel2();

    @Query(value = "SELECT" +
            "   prv.province_id AS locationId" +
            "   ,prv.name" +
            "   ,prv.code" +
            "   ,dtr.district_id AS districtId" +
            "   ,dtr.name AS districtName" +
            "   ,dtr.code AS districtCode" +
            "   ,sbd.subdistrict_id AS subdistrictId" +
            "   ,sbd.name AS subdistrictName" +
            "   ,sbd.code AS subdistrictCode" +
            "   ,deli.ffm_id AS FulfilmentId" +
            "   ,ffm.name AS fulfilmentName" +
            "   ,ffm.shortname AS fulfilmentShortname" +
            "   ,deli.lastmile_id AS lastmileId" +
            "   ,lm.name AS lastmileName" +
            "   ,lm.shortname AS lastmileShortname" +
            "   ,deli.warehouse_id AS warehouseId" +
            "   ,wh.warehouse_name AS warehouseName" +
            "   ,wh.warehouse_shortname AS warehouseShortname" +
            "   FROM" +
            "   lc_province AS prv" +
            "   LEFT JOIN" +
            "   lc_district AS dtr" +
            "   ON" +
            "   dtr.province_id = prv.province_id" +
            "   LEFT JOIN" +
            "   lc_subdistrict AS sbd" +
            "   ON" +
            "   sbd.district_id = dtr.district_id" +
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
            "   ORDER BY" +
            "   sbd.subdistrict_id", nativeQuery = true)
    Set<LocationResultSet> getByLevel3();

    @Modifying
    @Transactional
    @Query(value = "UPDATE" +
            "   cf_default_delivery" +
            "   SET" +
            "   ffm_id = :#{#deliveryDTO.fulfilmentId}" +
            "   ,lastmile_id = :#{#deliveryDTO.lastmileId}" +
            "   ,warehouse_id = :#{#deliveryDTO.warehouseId}" +
            "   WHERE" +
            "   location_id = :provinceId", nativeQuery = true)
    void updateByLevel1(@Param("provinceId") long provinceId,@Param("deliveryDTO") DeliveryDTO deliveryDTO);

    @Modifying
    @Transactional
    @Query(value = "UPDATE " +
            "   cf_default_delivery" +
            "   SET" +
            "   ffm_id = :#{#deliveryDTO.fulfilmentId}" +
            "   ,lastmile_id = :#{#deliveryDTO.lastmileId}" +
            "   ,warehouse_id = :#{#deliveryDTO.warehouseId}" +
            "   WHERE" +
            "   location_id IN" +
            "   (SELECT " +
            "   dtr.district_id" +
            "   FROM " +
            "   lc_district AS dtr" +
            "   WHERE" +
            "   dtr.province_id = :provinceId)", nativeQuery = true)
    void updateByLevel2(@Param("provinceId") long provinceId,@Param("deliveryDTO") DeliveryDTO deliveryDTO);

    @Modifying
    @Transactional
    @Query(value = "UPDATE " +
            "   cf_default_delivery" +
            "   SET" +
            "   ffm_id = :#{#deliveryDTO.fulfilmentId}" +
            "   ,lastmile_id = :#{#deliveryDTO.lastmileId}" +
            "   ,warehouse_id = :#{#deliveryDTO.warehouseId}" +
            "   WHERE" +
            "   location_id IN" +
            "   (SELECT" +
            "   sbd.subdistrict_id" +
            "   FROM" +
            "   lc_subdistrict AS sbd" +
            "   LEFT JOIN" +
            "   lc_district AS dtr" +
            "   ON" +
            "   sbd.district_id = dtr.district_id" +
            "   WHERE" +
            "   dtr.province_id = :provinceId)", nativeQuery = true)
    void updateByLevel3(@Param("provinceId") long provinceId, @Param("deliveryDTO") DeliveryDTO deliveryDTO);
}

/*
 *  @Repository: đánh dấu một class là tầng giao tiếp với database. Nó giúp chuyển đổi các database exception sang
 *  Spring-based unchecked exception, giúp cho việc xử lý exception trở nên dễ dàng hơn.
 *  @Param: để ánh xạ giá trị được truyền vào phương thức vào biến tương ứng trong câu lệnh sql native.
 *
 *  Chú ý:
 *  + Interface Repository không thể thiếu extends JpaRepository<Province, Long>
 *  + Interface Repository không thể thiếu annotation @Repository
 */

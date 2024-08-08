package com.octl2.api.repository;

import com.octl2.api.consts.Const;
import com.octl2.api.dto.LocationResultSet;
import com.octl2.api.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query(value = "SELECT" +
            "   deli.ffm_id AS fulfilmentId" +
            "   ,ptn_ffm.name AS fulfilmentName" +
            "   ,ptn_ffm.shortname AS fulfilmentShortname" +
            "   ,deli.lastmile_id AS lastmileId" +
            "   ,ptn_lm.name AS lastmileName" +
            "   ,ptn_lm.shortname AS lastmileShortname" +
            "   ,deli.warehouse_id AS warehouseId" +
            "   ,wh.warehouse_name AS warehouseName" +
            "   ,wh.warehouse_shortname AS warehouseShortname" +
            "   FROM" +
            "   cf_default_delivery AS deli" +
            "   LEFT JOIN" +
            "   bp_partner AS ptn_ffm" +
            "   ON" +
            "   ptn_ffm.partner_id = deli.ffm_id" +
            "   LEFT JOIN" +
            "   bp_partner AS ptn_lm" +
            "   ON" +
            "   ptn_lm.partner_id = deli.lastmile_id" +
            "   LEFT JOIN" +
            "   bp_warehouse AS wh" +
            "   ON" +
            "   wh.warehouse_id = deli.warehouse_id" +
            "   WHERE" +
            "   ptn_ffm.partner_type = " + Const.PARTNER_FFM +
            "   AND" +
            "   ptn_lm.partner_type = " + Const.PARTNER_LM +
            "   ORDER BY" +
            "   deli.ffm_id" +
            "   ,deli.lastmile_id" +
            "   ,deli.warehouse_id", nativeQuery = true)
    Set<LocationResultSet> getAllLogistic();
}

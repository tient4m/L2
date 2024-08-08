package com.octl2.api.repository;

import com.octl2.api.consts.Const;
import com.octl2.api.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    @Query(value = "SELECT" +
            "   CASE WHEN" +
            "   COUNT(ptn) > 0" +
            "   THEN TRUE ELSE FALSE END" +
            "   FROM" +
            "   bp_partner AS ptn" +
            "   WHERE" +
            "   ptn.partner_id = :fulfilmentId" +
            "   AND" +
            "   ptn.partner_type = " + Const.PARTNER_FFM, nativeQuery = true)
    boolean existsByFulfilmentId(Long fulfilmentId);

    @Query(value = "SELECT" +
            "   CASE WHEN" +
            "   COUNT(ptn) > 0" +
            "   THEN TRUE ELSE FALSE END" +
            "   FROM" +
            "   bp_partner AS ptn" +
            "   WHERE" +
            "   ptn.partner_id = :lastmileId" +
            "   AND" +
            "   ptn.partner_type = " + Const.PARTNER_LM, nativeQuery = true)
    boolean existsByLastmileId(Long lastmileId);
}

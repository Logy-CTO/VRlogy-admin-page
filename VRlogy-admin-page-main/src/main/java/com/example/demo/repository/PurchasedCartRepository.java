package com.example.demo.repository;

import com.example.demo.domain.PurchasedCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface PurchasedCartRepository extends JpaRepository<PurchasedCart, Long> {
    @Query("SELECT COALESCE(SUM(pc.totalPrice), 0) FROM PurchasedCart pc WHERE pc.purchaseDate BETWEEN :startDate AND :endDate")
    BigDecimal sumAmountByPurchaseDateBetween(@Param("startDate") Date startDate,
                                              @Param("endDate") Date endDate);
    List<PurchasedCart> findByPurchaseDateBetween(Date startDate, Date endDate);

    void deleteByApplyNum(String applyNum);

}


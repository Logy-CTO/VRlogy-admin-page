package com.example.demo.controller;

import com.example.demo.domain.PurchasedCart;
import com.example.demo.domain.SalesData;
import com.example.demo.repository.PurchasedCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PurchasedCartController {

    @Autowired
    private PurchasedCartRepository purchasedCartRepository;

    @GetMapping("/purchased-cart")
    public List<PurchasedCart> getAllPurchasedCarts() {
        return purchasedCartRepository.findAll();
    }

    @GetMapping("/purchased-cart/monthly-earnings")
    public BigDecimal getMonthlyEarnings() {
        // 이번 달의 시작일과 종료일을 계산합니다.
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // 해당 기간 내의 purchase_date에 해당하는 amount 값을 합산합니다.
        BigDecimal monthlyEarnings = purchasedCartRepository.sumAmountByPurchaseDateBetween(startDate, currentDate);

        return monthlyEarnings != null ? monthlyEarnings : BigDecimal.ZERO;
    }

    @GetMapping("/purchased-cart/previous-month-earnings")
    public BigDecimal getPreviousMonthEarnings() {
        // 이번 달의 시작일과 종료일을 계산합니다.
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // 이전 달로 설정합니다.
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();

        // 해당 기간 내의 purchase_date에 해당하는 amount 값을 합산합니다.
        BigDecimal previousMonthEarnings = purchasedCartRepository.sumAmountByPurchaseDateBetween(startDate, endDate);

        return previousMonthEarnings != null ? previousMonthEarnings : BigDecimal.ZERO;
    }

    @GetMapping("/purchased-cart/yearly-breakup")
    public Map<String, Object> getYearlyBreakup() {
        Map<String, Object> response = new HashMap<>();

        // 이번 해의 시작일과 종료일을 계산합니다.
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.set(currentYear, 0, 1, 0, 0, 0);
        Date startDate = calendar.getTime();
        calendar.set(currentYear + 1, 0, 1, 0, 0 ,0);
        Date endDate = calendar.getTime();

        // 해당 기간 내의 purchase_date에 해당하는 amount 값을 합산합니다.
        BigDecimal yearlyBreakup = purchasedCartRepository.sumAmountByPurchaseDateBetween(startDate, endDate);

        response.put("yearlyBreakup", yearlyBreakup != null ? yearlyBreakup : BigDecimal.ZERO);

        // 현재 연도와 이전 연도에 대한 정보를 설정합니다.
        response.put("currentYearValue", currentYear);
        response.put("previousYears", Arrays.asList(2022)); // 예시로 고정된 값인 2022를 사용하였습니다.

        return response;
    }
    @GetMapping("/api/purchased-cart/sales-overview")
    public List<SalesData> getSalesOverview(@RequestParam("month") int month) {
        // 이번 달의 시작일과 종료일을 계산합니다.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1); // 0부터 시작하는 인덱스로 변환하기 위해 -1을 해줍니다.
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endDate = calendar.getTime();

        // 해당 기간 내의 매출 데이터를 조회합니다.
        List<PurchasedCart> salesDataList = purchasedCartRepository.findByPurchaseDateBetween(startDate, endDate);

        // SalesData 리스트로 변환하여 반환합니다.
        List<SalesData> result = new ArrayList<>();

        for (PurchasedCart cart : salesDataList) {
            String dateStr = cart.getPurchaseDate().toString();
            BigDecimal revenue = cart.getAmount();
            result.add(new SalesData(dateStr, revenue));
        }

        return result;
    }

}

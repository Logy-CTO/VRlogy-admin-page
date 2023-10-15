package com.example.demo.domain;

import java.math.BigDecimal;

public class SalesData {
    private String date;
    private BigDecimal revenue;

    public SalesData(String date, BigDecimal revenue) {
        this.date = date;
        this.revenue = revenue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }
}

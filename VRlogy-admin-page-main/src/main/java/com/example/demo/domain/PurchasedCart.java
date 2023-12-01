package com.example.demo.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "purchased_cart")
public class PurchasedCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "member_id")
    private String memberEmail;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @Column(name = "apply_num")
    private String applyNum;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName= productName;
    }

    public String getMemberEmail() {
        return memberEmail ;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail= memberEmail ;
    }

    public Date getPurchaseDate() {
        return purchaseDate ;
    }

    public void setPurchaseDate(Date purchaseDate ) {
        this.purchaseDate  = purchaseDate ;
    }

    public	String	getApplyNum () {
        return applyNum ;
    }

    public void	setApplyNum (String applyNum ) {
        this.applyNum  = applyNum ;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}

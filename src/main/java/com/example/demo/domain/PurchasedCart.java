package com.example.demo.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

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

    private BigDecimal amount;

    @Column(name = "member_id")
    private String memberEmail;

    @Column(name = "purchase_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "apply_num")
    private String applyNum;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount= amount;
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

    public String getName() {
        return name ;
    }

    public void setName(String name ) {
        this.name  = name ;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address= address;
    }

    public String getPostCode () {
        return postCode ;
    }

    public void setPostCode (String postCode ) {
        this.postCode  = postCode ;
    }

    public 	String	getPhoneNumber () {
        return phoneNumber ;
    }

    public	void	setPhoneNumber (String phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public	String	getApplyNum () {
        return applyNum ;
    }

    public void	setApplyNum (String applyNum ) {
        this.applyNum  = applyNum ;
    }

}

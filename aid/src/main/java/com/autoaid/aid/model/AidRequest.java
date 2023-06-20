package com.autoaid.aid.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AidRequest {
    @Id
    @GeneratedValue
    private Long id;
    private int userId;
    private int providerId;
    private int breakdownId;
    private String carModel;
    private String errorCode;
    private String description;
    private String status;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aid_location")
    private GeoLocation location;
    private String aidProvider;
    private String carDriver;
    private Date timeStamp;
    private double billAmount;
    private String paymentStatus;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public String getAidProvider() {
        return aidProvider;
    }

    public void setAidProvider(String aidProvider) {
        this.aidProvider = aidProvider;
    }

    public String getCarDriver() {
        return carDriver;
    }

    public void setCarDriver(String carDriver) {
        this.carDriver = carDriver;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getBreakdownId() {
        return breakdownId;
    }

    public void setBreakdownId(int breakdownId) {
        this.breakdownId = breakdownId;
    }
}

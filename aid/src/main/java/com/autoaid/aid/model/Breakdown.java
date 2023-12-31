package com.autoaid.aid.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Breakdown {

    @Id
    @GeneratedValue
    private Long id;

    private int userId;
    private int providerId;
    private String providerName;
    @JsonProperty("model")
    private String model;
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date timestamp;
    @JsonProperty("location")
    @OneToOne
    @JoinColumn(name = "breakdown_location")
    private GeoLocation location;
    @JsonProperty("hardware_fault_code")
    private String errorCode;
    private boolean isResolved;
    private String status;

    public void setModel(String model) {
        this.model = model;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getModel() {
        return model;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }
}

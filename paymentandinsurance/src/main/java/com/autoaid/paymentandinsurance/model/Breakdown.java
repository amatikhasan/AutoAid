package com.autoaid.paymentandinsurance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
public class Breakdown {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("model")
    private String model;
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date timestamp;
    @JsonProperty("location")
    @OneToOne
    private GeoLocation location;
    @JsonProperty("hardware_fault_code")
    private String errorCode;

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
}

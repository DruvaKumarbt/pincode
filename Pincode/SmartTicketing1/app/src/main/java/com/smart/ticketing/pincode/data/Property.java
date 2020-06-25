package com.smart.ticketing.pincode.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class Property implements Serializable {

    String objectId;

    String aadharId;

    String name;

    String phone;

    String pid;

    String pincode;

    String addressLine1;

    String addressLine2;

    String city;

    String kathaNo;

    String fromWhom;

    String toWhom;

    String dateOfReg;

    String regNo;

    String latlng;
}

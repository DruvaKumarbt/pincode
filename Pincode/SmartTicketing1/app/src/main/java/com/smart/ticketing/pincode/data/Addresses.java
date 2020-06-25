package com.smart.ticketing.pincode.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class Addresses implements Serializable {

    String name;

    String phone;

    String pincode;

    String addressLine1;

    String addressLine2;

    String city;

    String latlng;

    String propertyId;

    String objectId;
}

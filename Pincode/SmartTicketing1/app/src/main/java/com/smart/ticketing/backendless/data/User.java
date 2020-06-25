package com.smart.ticketing.backendless.data;

import java.io.Serializable;

import lombok.Data;

/**
 * Created by dayanand on 27/03/17.
 */


@Data
public class User implements Serializable {

    String objectId;

    String username;

    String password;

    String name;

    String phone;

    String address;

    String department;

    String locality;

    String bloodGroup;

    String fatherPhone;

    String usn;

    String type;

    String imageUrl;

    String sem;

    String branch;

    String subject;

}

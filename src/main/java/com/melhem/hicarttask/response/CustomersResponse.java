package com.melhem.hicarttask.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CustomersResponse {
    private Long customerId;
    private String customerName ;
    private String customerAddress;
    private String customerPhone;
    private String addedBy;
    private Date createdAt ;

}

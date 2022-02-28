package com.melhem.hicarttask.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCustomerRequest {
    private String customerName ;
    private String customerAddress;
    private String customerPhone;

}

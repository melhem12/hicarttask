package com.melhem.hicarttask.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateOrderRequest {
    private Long orderId ;
    private Long customerId ;
    private double amount ;
    private int quantity;

}

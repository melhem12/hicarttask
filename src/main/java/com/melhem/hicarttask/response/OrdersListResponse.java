package com.melhem.hicarttask.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdersListResponse {
    private Long orderId ;
    private double amount ;
    private int quantity;
    private String  addedBy ;
    private String orderDate ;
    private String customerName ;
    private String customerAddress ;
    private String customerPhone ;
    private Long customerId ;

}

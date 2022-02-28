package com.melhem.hicarttask.controllers;

import com.melhem.hicarttask.requests.AddCustomerRequest;
import com.melhem.hicarttask.requests.AddOrderRequest;
import com.melhem.hicarttask.requests.UpdateCustomerRequest;
import com.melhem.hicarttask.requests.UpdateOrderRequest;
import com.melhem.hicarttask.response.CustomersResponse;
import com.melhem.hicarttask.response.OrdersListResponse;
import com.melhem.hicarttask.response.Response;
import com.melhem.hicarttask.services.CustomerService;
import com.melhem.hicarttask.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/orders")
public class OrderController extends  BaseController {
    @Autowired
    OrderService orderService ;
    @PostMapping("/addOrder")
    private ResponseEntity<Response> addOrder (@RequestBody AddOrderRequest addOrderRequest){
        Response    response= orderService.addOrder(addOrderRequest,   getCurrentUser());
        System.out.println(getCurrentUser().getName());
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @GetMapping("/orderList")
    private ResponseEntity<Response<OrdersListResponse>> getAllOrders (){
        Response <OrdersListResponse>   response= orderService.getAllOrders();
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @PutMapping ("/updateOrder")
    private ResponseEntity<Response> updateOrder (@RequestBody UpdateOrderRequest updateOrderRequest){
        Response    response= orderService.updateOrder(updateOrderRequest,   getCurrentUser());
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @DeleteMapping ("/deleteOrderById")
    private ResponseEntity<Response> deleteOrderById (@RequestParam  (name = "orderId")Long orderId ){
        Response    response= orderService.deleteOrder(orderId );
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @GetMapping("/getOrdersById")
    private ResponseEntity<Response<OrdersListResponse>> getOrdersById ( @RequestParam (name = "orderId")Long orderId ){
        Response <OrdersListResponse>   response= orderService.getOrdersById(orderId);
        return new ResponseEntity( response , HttpStatus.OK);
    }

}

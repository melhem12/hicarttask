package com.melhem.hicarttask.controllers;

import com.melhem.hicarttask.entitiy.Customer;
import com.melhem.hicarttask.requests.AddCustomerRequest;
import com.melhem.hicarttask.response.Response;
import com.melhem.hicarttask.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MappedSuperclass;

@RestController
@RequestMapping(value="/api/v1/customer")
public class CustomerController  extends BaseController{
    @Autowired
    CustomerService customerService ;
    @PostMapping("/addCustomer")
    private ResponseEntity<Response> addCustomer (@RequestBody AddCustomerRequest addCustomerRequest){
     Response    response= customerService.addCustomer(addCustomerRequest,   getCurrentUser());
        return new ResponseEntity( response , HttpStatus.OK);
    }
}

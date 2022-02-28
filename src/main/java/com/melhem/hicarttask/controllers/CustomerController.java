package com.melhem.hicarttask.controllers;

import com.melhem.hicarttask.entitiy.Customer;
import com.melhem.hicarttask.requests.AddCustomerRequest;
import com.melhem.hicarttask.requests.UpdateCustomerRequest;
import com.melhem.hicarttask.response.CustomersResponse;
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
        System.out.println(getCurrentUser().getName());
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @GetMapping("/customerList")
    private ResponseEntity<Response<CustomersResponse>> getAllCustomers (){
        Response <CustomersResponse>   response= customerService.getAllCustomers();
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @PutMapping ("/updateCustomer")
    private ResponseEntity<Response> updateCustomer (@RequestBody UpdateCustomerRequest updateCustomerRequest){
        Response    response= customerService.updateCustomer(updateCustomerRequest,   getCurrentUser());
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @DeleteMapping ("/deleteCustomerById")
    private ResponseEntity<Response> deleteCustomerById (@RequestParam  (name = "customerId")Long customerId ){
        Response    response= customerService.deleteCustomer(customerId );
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @GetMapping("/getCustomersById")
    private ResponseEntity<Response<CustomersResponse>> getCustomersById ( @RequestParam (name = "customerId")Long customerId ){
        Response <CustomersResponse>   response= customerService.getACustomersById(customerId);
        return new ResponseEntity( response , HttpStatus.OK);
    }
    @GetMapping("/getCustomerByName")
    private ResponseEntity<Response<CustomersResponse>> getCustomerByName ( @RequestParam (name = "customerName")String customerName ){
        Response <CustomersResponse>   response= customerService.getACustomerByName(customerName);
        return new ResponseEntity( response , HttpStatus.OK);
    }
}

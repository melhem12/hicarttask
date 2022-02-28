package com.melhem.hicarttask.services;

import com.melhem.hicarttask.entitiy.Customer;
import com.melhem.hicarttask.repositories.DB;
import com.melhem.hicarttask.requests.AddCustomerRequest;
import com.melhem.hicarttask.response.Dialog;
import com.melhem.hicarttask.response.Response;
import com.melhem.hicarttask.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CustomerService {
    @Autowired
    private DB db;


    public Response<Void> addCustomer(AddCustomerRequest  addCustomerRequest , AppUser user){
        Response response = new Response();
        response.setStatus(true);
        Dialog dialog = new Dialog();
        dialog.setTitle("customer  was  added");
        dialog.setTitle("success Add ");
        response.setDialog(dialog);
        Customer customer = new Customer();
        customer.setCustomerName(addCustomerRequest.getCustomerName());
        customer.setCustomerPhone(addCustomerRequest.getCustomerPhone());
        customer.setCreatedAt(new Date());
        customer.setAddedBy(user);
        db.customerRepos.save(customer);
        return response;
    }
}

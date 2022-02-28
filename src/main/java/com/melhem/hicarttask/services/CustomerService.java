package com.melhem.hicarttask.services;

import com.melhem.hicarttask.entitiy.Customer;
import com.melhem.hicarttask.error.NotFoundException;
import com.melhem.hicarttask.repositories.DB;
import com.melhem.hicarttask.requests.AddCustomerRequest;
import com.melhem.hicarttask.requests.UpdateCustomerRequest;
import com.melhem.hicarttask.response.CustomersResponse;
import com.melhem.hicarttask.response.Dialog;
import com.melhem.hicarttask.response.Response;
import com.melhem.hicarttask.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private DB db;


    public Response<Void> addCustomer(AddCustomerRequest addCustomerRequest, AppUser user) {
        Response response = new Response();
        response.setStatus(true);
        Dialog dialog = new Dialog();
        dialog.setTitle("customer  was  added");
        dialog.setTitle("success Add ");
        response.setDialog(dialog);
        Customer customer = new Customer();
        customer.setCustomerName(addCustomerRequest.getCustomerName());
        customer.setCustomerPhone(addCustomerRequest.getCustomerPhone());
        customer.setCustomerAddress(addCustomerRequest.getCustomerAddress());
        customer.setCreatedAt(new Date());
        customer.setAddedBy(user);
        db.customerRepos.save(customer);
        return response;
    }

    public Response<CustomersResponse> getAllCustomers() {
        Response response = new Response();
        Dialog dialog = new Dialog();
        List<Customer> customerList = db.customerRepos.findAll();
        List<CustomersResponse> customersResponseList = new ArrayList<>();
        customerList.stream().forEach(customer -> {
            CustomersResponse customersResponse = new CustomersResponse();
            customersResponse.setCustomerId(customer.getId());
            customersResponse.setCustomerAddress(customer.getCustomerAddress());
            customersResponse.setCustomerPhone(customer.getCustomerPhone());
            customersResponse.setCreatedAt(customer.getCreatedAt());
            customersResponse.setCustomerName(customer.getCustomerName());
            customersResponse.setAddedBy(customer.getAddedBy().getName());
            customersResponseList.add(customersResponse);
        });
        response.setData(customersResponseList);
        response.setStatus(true);
        return response;
    }

    public Response updateCustomer(UpdateCustomerRequest updateCustomerRequest, AppUser currentUser) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Optional<Customer> customer = db.customerRepos.findById(updateCustomerRequest.getCustomerId());
        if (!customer.isPresent()) {
            dialog.setTitle("not found");
            response.setStatus(false);
            response.setDialog(dialog);
            dialog.setMessage(" Customer not found");
            response.setDialog(dialog);

        }
        customer.ifPresent(myCustomer -> {
            myCustomer.setCustomerName(updateCustomerRequest.getCustomerName());
            myCustomer.setCustomerPhone(updateCustomerRequest.getCustomerPhone());
            myCustomer.setCustomerAddress(updateCustomerRequest.getCustomerAddress());
            db.customerRepos.save(myCustomer);

        });
        dialog.setTitle("update success");
        dialog.setMessage("Customer update success");
        response.setStatus(true);

        response.setDialog(dialog);
        return response;
    }

    public Response<CustomersResponse> getACustomersById(Long customerId) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Optional<Customer> customer = db.customerRepos.findById(customerId);
        if (!customer.isPresent()) {
            dialog.setTitle("not found");
            response.setStatus(false);

            dialog.setMessage(" Customer not found");
            response.setDialog(dialog);
            return response;
        }

        CustomersResponse customersResponse = new CustomersResponse();
        customer.ifPresent(myCustomer -> {
            customersResponse.setCustomerId(myCustomer.getId());
            customersResponse.setCustomerAddress(myCustomer.getCustomerAddress());
            customersResponse.setCustomerPhone(myCustomer.getCustomerPhone());
            customersResponse.setCreatedAt(myCustomer.getCreatedAt());
            customersResponse.setCustomerName(myCustomer.getCustomerName());
            customersResponse.setAddedBy(myCustomer.getAddedBy().getName());
        });

        response.setData(customersResponse);
        response.setStatus(true);

        return response;
    }

    public Response<CustomersResponse> getACustomerByName(String customerName) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Optional<Customer> customer = db.customerRepos.findByCustomerName(customerName);
        if (!customer.isPresent()) {
            dialog.setTitle("not found");
            response.setStatus(false);

            dialog.setMessage(" Customer not found");
            response.setDialog(dialog);
            return response;
        }

        CustomersResponse customersResponse = new CustomersResponse();
        customer.ifPresent(myCustomer -> {
            customersResponse.setCustomerId(myCustomer.getId());
            customersResponse.setCustomerAddress(myCustomer.getCustomerAddress());
            customersResponse.setCustomerPhone(myCustomer.getCustomerPhone());
            customersResponse.setCreatedAt(myCustomer.getCreatedAt());
            customersResponse.setCustomerName(myCustomer.getCustomerName());
            customersResponse.setAddedBy(myCustomer.getAddedBy().getName());
        });

        response.setData(customersResponse);
        response.setStatus(true);

        return response;
    }

    public Response deleteCustomer(Long customerId) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Optional<Customer> customer = db.customerRepos.findById(customerId);
        if (!customer.isPresent()) {
            dialog.setTitle("not found");
            response.setStatus(false);
            response.setDialog(dialog);
            dialog.setMessage(" Customer not found");
            response.setDialog(dialog);

        }
        customer.ifPresent(myCustomer -> {
            db.customerRepos.delete(myCustomer);

        });
        dialog.setTitle("delete done");
        dialog.setMessage("Customer was deleted");
        response.setStatus(true);
        response.setDialog(dialog);
        return response;
    }
}

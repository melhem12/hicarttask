package com.melhem.hicarttask.services;

import com.melhem.hicarttask.entitiy.Customer;
import com.melhem.hicarttask.entitiy.Order;
import com.melhem.hicarttask.repositories.DB;
import com.melhem.hicarttask.requests.AddOrderRequest;
import com.melhem.hicarttask.requests.UpdateOrderRequest;
import com.melhem.hicarttask.response.CustomersResponse;
import com.melhem.hicarttask.response.Dialog;
import com.melhem.hicarttask.response.OrdersListResponse;
import com.melhem.hicarttask.response.Response;
import com.melhem.hicarttask.security.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private DB db;

    public Response addOrder(AddOrderRequest addOrderRequest, AppUser currentUser) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Order order = new Order();
        Optional<Customer> customer = db.customerRepos.findById(addOrderRequest.getCustomerId());
        customer.ifPresentOrElse(
                (myCustomer)
                        -> {
                    order.setCustomer(myCustomer);
                    order.setOrderDate(new Date());
                    order.setAddedBy(currentUser);
                    order.setQuantity(addOrderRequest.getQuantity());
                    order.setAmount(addOrderRequest.getAmount());
                    db.orderRepos.save(order);
                    response.setStatus(true);

                    dialog.setMessage("success add order ");
                    dialog.setTitle("success add order");
                    response.setDialog(dialog);

                },
                ()
                        -> {

                    dialog.setMessage("Customer Not found");
                    dialog.setTitle("Customer Not found");
                    response.setDialog(dialog);
                    response.setStatus(false);
                });


        return response;
    }


    public Response<OrdersListResponse> getAllOrders() {
        Response response = new Response();
        Dialog dialog = new Dialog();
        List<Order> orderList = db.orderRepos.findAll();
        List<OrdersListResponse> ordersListResponses = new ArrayList<>();
        orderList.stream().forEach(order -> {
            OrdersListResponse ordersListResponse = new OrdersListResponse();
            ordersListResponse.setCustomerId(order.getCustomer().getId());
            ordersListResponse.setAddedBy(order.getAddedBy().getName());
            ordersListResponse.setCustomerAddress(order.getCustomer().getCustomerAddress());
            ordersListResponse.setCustomerPhone(order.getCustomer().getCustomerPhone());
            ordersListResponse.setQuantity(order.getQuantity());
            ordersListResponse.setCustomerName(order.getCustomer().getCustomerName());

            ordersListResponse.setOrderDate(order.getOrderDate().toString());
            ordersListResponse.setAmount(order.getAmount());
            ordersListResponse.setOrderId(order.getId());
            ordersListResponses.add(ordersListResponse);
        });
        response.setData(ordersListResponses);
        response.setStatus(true);
        return response;
    }

    public Response updateOrder(UpdateOrderRequest updateOrderRequest, AppUser currentUser) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Optional<Order> order = db.orderRepos.findById(updateOrderRequest.getOrderId());
        order.ifPresentOrElse(
                (myOrder)
                        -> {


                    Optional<Customer> customer = db.customerRepos.findById(updateOrderRequest.getCustomerId());
                    customer.ifPresentOrElse(
                            (myCustomer)
                                    -> {
                                myOrder.setAmount(updateOrderRequest.getAmount());
                                myOrder.setQuantity(updateOrderRequest.getQuantity());
                                myOrder.setCustomer(myCustomer);
                                response.setStatus(true);


                                dialog.setMessage("success add order ");
                                dialog.setTitle("success add order");
                                response.setDialog(dialog);

                            },
                            ()
                                    -> {

                                dialog.setMessage("Customer Not found");
                                dialog.setTitle("Customer Not found");
                                response.setDialog(dialog);
                                response.setStatus(false);
                            });


                    dialog.setMessage("success update order ");
                    dialog.setTitle("success update order");
                    response.setDialog(dialog);

                },
                ()
                        -> {

                    dialog.setMessage("Order Not found");
                    dialog.setTitle("Order Not found");
                    response.setDialog(dialog);
                    response.setStatus(false);
                });
        return response;
    }

    public Response deleteOrder(Long orderId) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Optional<Order> order = db.orderRepos.findById(orderId);
        order.ifPresentOrElse(
                (myOrder)
                        -> {
           db.orderRepos.delete(myOrder);
                    dialog.setMessage("Order was delete");
                    dialog.setTitle("Order was  deleted");
                    response.setDialog(dialog);
                    response.setStatus(true);

                },
                ()
                        -> {

                    dialog.setMessage("Order Not found");
                    dialog.setTitle("Order Not found");
                    response.setDialog(dialog);
                    response.setStatus(false);
                });
        return response;
    }


    public Response<OrdersListResponse> getOrdersById(Long orderId) {
        Response response = new Response();
        Dialog dialog = new Dialog();
        Optional<Order> order = db.orderRepos.findById(orderId);
        OrdersListResponse ordersListResponse = new OrdersListResponse();

        order.ifPresentOrElse(
                (myOrder)
                        -> {
                    ordersListResponse.setCustomerId(myOrder.getCustomer().getId());
                    ordersListResponse.setAddedBy(myOrder.getAddedBy().getName());
                    ordersListResponse.setCustomerAddress(myOrder.getCustomer().getCustomerAddress());
                    ordersListResponse.setCustomerPhone(myOrder.getCustomer().getCustomerPhone());
                    ordersListResponse.setQuantity(myOrder.getQuantity());
                    ordersListResponse.setCustomerName(myOrder.getCustomer().getCustomerName());
                    ordersListResponse.setOrderDate(myOrder.getOrderDate().toString());
                    ordersListResponse.setAmount(myOrder.getAmount());
                    ordersListResponse.setOrderId(myOrder.getId());

                    dialog.setMessage("Order not found");
                    dialog.setTitle("Order not found");
                    response.setDialog(dialog);
                    response.setStatus(true);
                    response.setData(ordersListResponse);

                },
                ()
                        -> {

                    dialog.setMessage("Order Not found");
                    dialog.setTitle("Order Not found");
                    response.setDialog(dialog);
                    response.setStatus(false);
                });
        return response;
    }
}

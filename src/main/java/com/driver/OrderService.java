package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryPartner deliveryPartner;
     public void addOrder(Order order)
     {
         String id = order.getId();
         orderRepository.addOrder(id,order);
     }

     public void addPartner(String id)
     {
         orderRepository.addPartner(id);
     }

     public void addOrderPartnerPair(String orderId, String partnerId)
     {
         orderRepository.addOrderPartnerPair(orderId,partnerId);
     }

     public Order getOrderById(String id)
     {
         return orderRepository.getOrderById(id);
     }

    public DeliveryPartner getPartnerById(String id)
    {
        return orderRepository.getPartnerById(id);
    }
    public int getOrderCountByPartnerId(String id)
    {
        return orderRepository.getOrderCountByPartnerId(id);
    }

    public List<String> getOrdersByPartnerId(String id)
    {
        List<String> ans = orderRepository.getOrdersByPartnerId(id);
        if(ans.isEmpty()) ans = null;
        return ans;
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.getAllOrders();
    }
    public int getCountOfUnassignedOrders()
    {
        return orderRepository.getCountOfUnassignedOrders();
    }
}

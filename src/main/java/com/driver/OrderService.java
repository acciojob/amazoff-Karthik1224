package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
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

     public void addOrderPartnerPair(String orderId,String partnerId)
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

    public List<Order> getOrdersByPartnerId(String id)
    {
        List<Order> ans = orderRepository.getOrdersByPartnerId(id);
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
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId)
    {
        String[] timeParts = time.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        int t = hours * 60 + minutes;
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(t,partnerId);
    }
    public String getLastDeliveryTimeByPartnerId(String id)
    {
        int deliveryTime = orderRepository.getLastDeliveryTimeByPartnerId(id);
        int hours = deliveryTime / 60;
        int minutes = deliveryTime % 60;
        // Format the hours and minutes as a 24-hour time string "HH:MM"
        String deliveryTimeStr = String.format("%02d:%02d", hours, minutes);
        return deliveryTimeStr;

    }
    public void deletePartnerById(String id)
    {
        orderRepository.deletePartnerById(id);
    }

    public void deleteOrderById(String id)
    {
        orderRepository.deleteOrderById(id);
    }
}

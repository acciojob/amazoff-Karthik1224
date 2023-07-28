package com.driver;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {

    @Autowired
     private DeliveryPartner deliveryPartner;
     HashMap<String,Order> order = new HashMap();
     HashMap<String,DeliveryPartner>deliverPartnerMap = new HashMap<>();

     HashMap<String,String>assignOrderToPartner = new HashMap<>();

     HashMap<String,Integer>numOfOrdersAssignedToPartner = new HashMap<>();

     public void addOrder(String id,Order orderItem)
     {
         order.put(id,orderItem);
     }
    public void addPartner(String id)
    {
        int numberOfOrders = deliveryPartner.getNumberOfOrders();
        DeliveryPartner obj = new DeliveryPartner(id);
        obj.setNumberOfOrders(numberOfOrders);

        deliverPartnerMap.put(id,obj);

    }

    public void addOrderPartnerPair(String orderId, String partnerId)
    {
         assignOrderToPartner.put(partnerId,orderId);

         numOfOrdersAssignedToPartner.put(partnerId,numOfOrdersAssignedToPartner.getOrDefault(partnerId,0)+1);

    }

    public Order getOrderById(String id)
    {
        if(order.containsKey(id))
        {
            return order.get(id);
        }
        else
        {
            return null;
        }
    }

    public DeliveryPartner getPartnerById(String id)
    {
        if(deliverPartnerMap.containsKey(id))
        {
            return deliverPartnerMap.get(id);
        }
        else
        {
            return null;
        }
    }
    public int getOrderCountByPartnerId(String id)
    {
       return numOfOrdersAssignedToPartner.get(id);
    }

    public List<String> getOrdersByPartnerId(String id)
    {
        List<String> ans = new ArrayList<>();
        if(assignOrderToPartner.containsKey(id))
        {
            String ord = assignOrderToPartner.get(id);
            ans.add(ord);
        }
        return ans;

    }

    public List<Order> getAllOrders()
    {
        List<Order> ans = new ArrayList<>();
        for(Order it:order.values())
        {
            ans.add(it);
        }
        return ans;
    }

    public int getCountOfUnassignedOrders()
    {
        int orderSize = order.size();
        int orderAssignToPartner = assignOrderToPartner.size();
        return orderSize-orderAssignToPartner;
    }
 }

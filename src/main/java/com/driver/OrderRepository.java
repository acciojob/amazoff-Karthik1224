package com.driver;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class OrderRepository {


     HashMap<String,Order> order = new HashMap();
     HashMap<String,DeliveryPartner>deliverPartnerMap = new HashMap<>();

     HashMap<String,List<Order>>assignOrderToPartner = new HashMap<>();


     public void addOrder(String id,Order orderItem)
     {
         order.put(id,orderItem);
     }
    public void addPartner(String id)
    {
        deliverPartnerMap.put(id,new DeliveryPartner(id));

    }

    public void addOrderPartnerPair(String orderId, String partnerId)
    {
         Order obj = order.get(orderId);
         if(assignOrderToPartner.containsKey(partnerId))
         {
             List<Order> list = assignOrderToPartner.get(partnerId);
             list.add(obj);
             assignOrderToPartner.put(partnerId,list);

         }
         else
         {
             List<Order> list = new ArrayList<>();
             list.add(obj);
             assignOrderToPartner.put(partnerId,list);
         }


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
         List<Order>list = assignOrderToPartner.get(id);
         return list.size();
    }

    public List<Order> getOrdersByPartnerId(String id)
    {

         List<Order> list = assignOrderToPartner.get(id);
         return list;
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
        int numberOfOrdersAssigned = 0;
        for(List<Order>it:assignOrderToPartner.values())
        {
            numberOfOrdersAssigned += it.size();
        }

        return orderSize - numberOfOrdersAssigned;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(int time,String partnerId)
    {
        List<Order> list = assignOrderToPartner.get(partnerId);

        int count=0;
        for(Order it:list)
        {
            if(it.getDeliveryTime() > time)
            {
                count++;
            }
        }
        return count;
    }
    public int getLastDeliveryTimeByPartnerId(String id)
    {
        List<Order> list = assignOrderToPartner.get(id);
        int time = Integer.MIN_VALUE;
        for(Order it:list)
        {
            time = Math.max(time,it.getDeliveryTime());
        }
        return time;
    }
    public void deletePartnerById(String id)
    {
         assignOrderToPartner.remove(id);

         deliverPartnerMap.remove(id);

    }
    public void deleteOrderById(String id)
    {
        order.remove(id);
        for(List<Order>it: assignOrderToPartner.values())
        {
            for(Order obj : it)
            {
                if(obj.getId().equals(id))
                {
                    it.remove(obj);
                }
            }
        }
    }
 }

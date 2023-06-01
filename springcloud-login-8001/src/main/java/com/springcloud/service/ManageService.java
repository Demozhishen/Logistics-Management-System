package com.springcloud.service;


import com.springcloud.entity.Order;
import com.springcloud.entity.Sign;

public interface ManageService {


     public Order CollectItem(Order order);


     public Sign SignItem(Sign sign);


}

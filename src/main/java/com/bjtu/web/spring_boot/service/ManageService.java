package com.bjtu.web.spring_boot.service;

import com.bjtu.web.spring_boot.entity.Order;
import com.bjtu.web.spring_boot.entity.Sign;

public interface ManageService {


     public Order CollectItem(Order order);


     public Sign SignItem(Sign sign);


}

package com.milkyteapos.service.Impl;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.OrderGood;
import com.milkyteapos.repository.GoodRepository;
import com.milkyteapos.repository.OrderGoodRepository;
import com.milkyteapos.service.IOrderGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("iOrderGoodService")
public class OrderGoodServiceImpl implements IOrderGoodService {

    @Autowired
    OrderGoodRepository orderGoodRepository;

    @Autowired
    GoodRepository goodRepository;

    @Override
    public ServerResponse addOrderGood(int orderId, List<OrderGood> orderGoodList) {
        OrderGood orderGood;
        for (int i = 0; i < orderGoodList.size(); i++){
            orderGood = orderGoodList.get(i);
            orderGood.setOrderId(orderId);
            orderGoodRepository.save(orderGood);
        }
        return ServerResponse.createBySuccessMessage("订单添加成功");
    }


    @Override
    public ServerResponse findOrderDetail(int orderId) {
        List<OrderGood> orderGoodList = orderGoodRepository.findByOrderId(orderId);
        List<Map> orderGoodList1 = new ArrayList<Map>();
        for(int i = 0; i< orderGoodList.size(); i++){
            Map map = new LinkedHashMap();
            map.put("id", orderGoodList.get(i).getId());
            map.put("orderId", orderGoodList.get(i).getOrderId());
            map.put("goodName", goodRepository.findById(orderGoodList.get(i).getGoodId()).getGoodName());
            map.put("temperature", orderGoodList.get(i).getTemperature());
            map.put("sugar", orderGoodList.get(i).getSugar());
            map.put("realPrice", orderGoodList.get(i).getRealPrice());
            map.put("sum", orderGoodList.get(i).getSum());
            map.put("ifAddPearl", orderGoodList.get(i).getIfAddPearl());
            map.put("ifAddCoconuts", orderGoodList.get(i).getIfAddCoconuts());
            orderGoodList1.add(map);
        }
        return ServerResponse.createBySuccess(orderGoodList1);
    }
}

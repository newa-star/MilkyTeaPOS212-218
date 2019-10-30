package com.milkyteapos.service.Impl;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.OrderInfo;
import com.milkyteapos.dataobject.OrderGood;
import com.milkyteapos.repository.OrderGoodRepository;
import com.milkyteapos.repository.OrderInfoRepository;
import com.milkyteapos.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iOrderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Autowired
    OrderGoodRepository orderGoodRepository;

    @Override
    public ServerResponse findAllOrderInfo() {
        List<OrderInfo> orderInfoList = orderInfoRepository.findAll();
        if (orderInfoList.size() == 0){
            return ServerResponse.createByErrorMessage("无订单信息");
        }
        return ServerResponse.createBySuccess(orderInfoList);
    }

    @Override
    public ServerResponse findOrderInfoByUserId(int userId) {
        List<OrderInfo> orderInfoList = orderInfoRepository.findByUserId(userId);
        if (orderInfoList.size() == 0){
            return ServerResponse.createByErrorMessage("无订单信息");
        }
        return ServerResponse.createBySuccess(orderInfoList);
    }

    @Override
    public ServerResponse findOrderDetail(int orderId) {
        List<OrderGood> orderGoodList = orderGoodRepository.findByOrderId(orderId);
        return ServerResponse.createBySuccess(orderGoodList);
    }
}

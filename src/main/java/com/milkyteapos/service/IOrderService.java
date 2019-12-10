package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;

import java.sql.Timestamp;

public interface IOrderService {
    ServerResponse findAllOrderInfo();
    ServerResponse findOrderInfoByUserId(int userId);
    ServerResponse addOrder(int userId, Timestamp currentTime, double totalPrice);
}

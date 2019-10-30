package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;

public interface IOrderService {
    ServerResponse findAllOrderInfo();
    ServerResponse findOrderInfoByUserId(int userId);
    ServerResponse findOrderDetail(int orderId);
}

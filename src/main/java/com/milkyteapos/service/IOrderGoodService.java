package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.OrderGood;
import java.util.List;

public interface IOrderGoodService {
    ServerResponse addOrderGood(int orderId, List<OrderGood> orderGoodList);
    ServerResponse findOrderDetail(int orderId);
}

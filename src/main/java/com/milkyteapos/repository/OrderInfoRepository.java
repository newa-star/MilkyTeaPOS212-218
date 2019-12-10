package com.milkyteapos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.milkyteapos.dataobject.OrderInfo;
import java.sql.Timestamp;
import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
    List<OrderInfo> findAll();
    List<OrderInfo> findByUserId(int userId);
    OrderInfo findByCreateTime(Timestamp createTime);
    OrderInfo save(OrderInfo orderInfo);
}

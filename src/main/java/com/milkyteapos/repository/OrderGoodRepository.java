package com.milkyteapos.repository;

import com.milkyteapos.dataobject.OrderGood;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderGoodRepository extends JpaRepository<OrderGood,Integer> {
    List<OrderGood> findByOrderId(int orderId);
}

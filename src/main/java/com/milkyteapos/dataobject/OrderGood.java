package com.milkyteapos.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class OrderGood {
    @Id
    private int id;
    private int orderId;
    private int goodId;
    private int temperature;
    private int sugar;
    private double realPrice;
    private int sum;
}

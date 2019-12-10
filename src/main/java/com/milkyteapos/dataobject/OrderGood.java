package com.milkyteapos.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class OrderGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int orderId;
    private int goodId;
    private int temperature;
    private int sugar;
    private double realPrice;
    private int sum;
    private int ifAddPearl;
    private int ifAddCoconuts;
}

package com.milkyteapos.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Order {

    @Id
    private int id;
    private int userId;
    private int state;
    private double total_price;
}

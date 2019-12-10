package com.milkyteapos.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class OrderInfo {

    @Id
    private int id;
    private int userId;
    private int state;
    private double totalPrice;
    private Timestamp createTime;
}

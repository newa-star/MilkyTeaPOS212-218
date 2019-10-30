package com.milkyteapos.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Good {

    @Id
    private int id;
    private String goodName;
    private double price;
    private int size;
    private int classify;
    private String picture;
}

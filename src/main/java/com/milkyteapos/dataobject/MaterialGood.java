package com.milkyteapos.dataobject;


import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MaterialGood {

    @Id
    private int id;
    private int goodId;
    private int materialId;
    private int goodSize;
    private int num;
}

package com.milkyteapos.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Material {

    @Id
    private int id;
    private String materialName;
    private int size;
    private int storage;
}

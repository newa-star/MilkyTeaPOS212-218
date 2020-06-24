package com.milkyteapos.dataobject;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ApplyAdmin{
    @Id
    private int id;
    private String code;
    private String userName;
    private String password;
    private int state;
}

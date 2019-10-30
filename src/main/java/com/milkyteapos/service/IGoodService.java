package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.Good;

public interface IGoodService {
    ServerResponse findAllGood();
    ServerResponse addGood(Good good);
}

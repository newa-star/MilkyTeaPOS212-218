package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.Good;

import java.io.IOException;

public interface IGoodService {
    ServerResponse addGood (Good good) throws Exception;
    ServerResponse findAllGood();
    ServerResponse findByClassify(int classify);
    ServerResponse updateGood(Good good) throws IOException;
    ServerResponse deleteGood(int goodId);
}

package com.milkyteapos.service.Impl;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.Good;
import com.milkyteapos.repository.GoodRepository;
import com.milkyteapos.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("iGoodService")
public class GoodServiceImpl implements IGoodService {

    @Autowired
    GoodRepository goodRepository;
    @Override
    public ServerResponse findAllGood() {
        List<Good> goodList = goodRepository.findAll();
        if (goodList.size() == 0){
            return ServerResponse.createByErrorMessage("无商品信息");
        }
        return ServerResponse.createBySuccess(goodList);
    }

    @Override
    public ServerResponse addGood(Good good) {
        Good good1 = goodRepository.findByGoodNameAndSize(good.getGoodName(), good.getSize());
        if (good1 != null)
            return ServerResponse.createByErrorMessage("已存在该商品");
        return ServerResponse.createBySuccess(goodRepository.save(good));
    }
}

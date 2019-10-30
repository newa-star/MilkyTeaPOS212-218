package com.milkyteapos.service.Impl;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.Good;
import com.milkyteapos.repository.GoodRepository;
import com.milkyteapos.service.IGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    @Override
    public ServerResponse updateGood(int goodId, String type, String value) {
        Good good1 = goodRepository.findById(goodId);
        if(type.equals("goodName")){
            if (value == good1.getGoodName()){
                return ServerResponse.createByErrorMessage("请修改为新的商品名称");
            }
            Good good2 = goodRepository.findByGoodName(value);
            if(good2 != null){
                return ServerResponse.createByErrorMessage("已存在相同商品名称");
            }
            good1.setGoodName(value);
        }else if (type.equals("price")){
            double price = Double.valueOf(value);
            if (price == good1.getPrice()){
                return ServerResponse.createByErrorMessage("请修改为新的商品价格");
            }
            good1.setPrice(price);
        }else if (type.equals("classify")){
            good1.setClassify(Integer.valueOf(value));
        }else if (type.equals("avatar")){
            good1.setPicture(value);
        }
        goodRepository.save(good1);
        return ServerResponse.createBySuccess(good1);
    }
}

package com.milkyteapos.service.Impl;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.Good;
import com.milkyteapos.repository.GoodRepository;
import com.milkyteapos.service.IGoodService;
import com.milkyteapos.utils.PropertiesUtil;
import jnr.ffi.annotations.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

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
//        Map goods = new LinkedHashMap();
//        for (int i = 0; i < goodList.size(); i++){
//            goods.put("good" + (i + 1), goodList.get(i));
//        }
        return ServerResponse.createBySuccess(goodList);
    }

    @Override
    public ServerResponse findByClassify(int classify) {
        List<Good> goodList = goodRepository.findByClassify(classify);
        if (goodList.size() == 0){
            return ServerResponse.createByErrorMessage("无商品信息");
        }
        return ServerResponse.createBySuccess(goodList);
    }

    @Override
    public ServerResponse findByKey(String key) {
        List<Good> goodList = goodRepository.findByGoodNameContains(key);
        boolean flag = true;
        for (int i = 0; i < key.length(); i++)
        {
            if (!Character.isDigit(key.charAt(i)))
                flag = false;
        }
        if(flag){
            Good good1 = goodRepository.findById((int)Integer.valueOf(key));
            goodList.add(0, good1);
        }
        return ServerResponse.createBySuccess(goodList);
    }

    @Override
    public ServerResponse addGood(Good good) throws Exception {
        Good good1 = goodRepository.findByGoodNameAndSize(good.getGoodName(), good.getSize());
        if (good1 != null)
            return ServerResponse.createByErrorMessage("已存在该商品");
        if(good.getPicture() != null){
            URL url = new URL(good.getPicture());
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            String pictureName = UUID.randomUUID().toString() + ".jpg";
            String filename = PropertiesUtil.getProperty("uploadFileDir") + "good/" + pictureName;
            File file = new File(filename);
            FileOutputStream os = new FileOutputStream(file, true);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
            good.setPicture("good/" + pictureName);
        }
        goodRepository.save(good);
        Good good2 = goodRepository.findByGoodNameAndSize(good.getGoodName(), good.getSize());
        return ServerResponse.createBySuccess(good2);
    }

    @Override
    public ServerResponse updateGood(Good good) throws IOException {
        Good good1 = goodRepository.findById(good.getId());
        if(good1 == null){
            return ServerResponse.createBySuccessMessage("该商品id不存在");
        }
        if(good.getGoodName() != null){
            good1.setGoodName(good.getGoodName());
        }
        if(good.getPrice() != 0){
            good1.setPrice(good.getPrice());
        }
        if(good.getPicture() != null){
            File oldFile = new File(PropertiesUtil.getProperty("uploadFileDir") + goodRepository.findById(good1.getId()).getPicture());
            if(oldFile.exists()){
                oldFile.delete();
            }
            URL url = new URL(good.getPicture());
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            String pictureName = UUID.randomUUID().toString() + ".jpg";
            String filename = PropertiesUtil.getProperty("uploadFileDir") + "good/" + pictureName;
            File file = new File(filename);
            FileOutputStream os = new FileOutputStream(file, true);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            is.close();
            good1.setPicture("good/" + pictureName);
        }
        goodRepository.save(good1);
        return ServerResponse.createBySuccess(good1);
    }

    @Override
    public ServerResponse deleteGood(int goodId) {
        Good good = goodRepository.findById(goodId);
        if(good == null){
            return ServerResponse.createByErrorMessage("该商品不存在");
        }
        File oldFile = new File(PropertiesUtil.getProperty("uploadFileDir") + goodRepository.findById(goodId).getPicture());
        if(oldFile.exists()){
            oldFile.delete();
        }
        goodRepository.deleteById(goodId);
        return ServerResponse.createBySuccessMessage("删除成功");
    }
}

package com.milkyteapos.service.Impl;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.OrderGood;
import com.milkyteapos.dataobject.OrderInfo;
import com.milkyteapos.repository.GoodRepository;
import com.milkyteapos.repository.OrderGoodRepository;
import com.milkyteapos.repository.OrderInfoRepository;
import com.milkyteapos.service.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("iFormService")
public class FormServiceImpl implements IFormService {

    @Autowired
    GoodRepository goodRepository;
    @Autowired
    OrderGoodRepository orderGoodRepository;
    @Autowired
    OrderInfoRepository orderInfoRepository;

    @Override
    public ServerResponse lineChart(String createYearStr) {
        int[] milkyTeaSum = new int[12];
        int[] fruitTeaSum = new int[12];
        int[] freshTeaSum = new int[12];
        int[] cheeseTeaSum = new int[12];
        Timestamp createMonth;
        Timestamp afterCreateMonth;
        for (int i = 0; i < 12; i++) {
            if (i == 11) {
                createMonth = Timestamp.valueOf(createYearStr + "-" + (i + 1) + "-01 00:00:00");
                afterCreateMonth = Timestamp.valueOf((Integer.valueOf(createYearStr) + 1) + "-01-01 00:00:00");
            } else {
                createMonth = Timestamp.valueOf(createYearStr + "-" + (i + 1) + "-01 00:00:00");
                afterCreateMonth = Timestamp.valueOf(createYearStr + "-" + (i + 2) + "-01 00:00:00");
            }
            List<OrderInfo> orderInfoList = orderInfoRepository.findByCreateTimeBetween(createMonth, afterCreateMonth);
            for (int j = 0; j < orderInfoList.size(); j++) {
                List<OrderGood> orderGoods = orderGoodRepository.findByOrderId(orderInfoList.get(j).getId());
                for (int k = 0; k < orderGoods.size(); k++) {
                    int classify = goodRepository.findById(orderGoods.get(k).getGoodId()).getClassify();
                    switch (classify) {
                        case 0:
                            milkyTeaSum[i] += orderGoods.get(k).getSum();
                            break;
                        case 1:
                            fruitTeaSum[i] += orderGoods.get(k).getSum();
                            break;
                        case 2:
                            freshTeaSum[i] += orderGoods.get(k).getSum();
                            break;
                        case 3:
                            cheeseTeaSum[i] += orderGoods.get(k).getSum();
                            break;
                    }
                }
            }
        }
        Map salesVolume = new LinkedHashMap();
        salesVolume.put("milkyTea", milkyTeaSum);
        salesVolume.put("fruitTea", fruitTeaSum);
        salesVolume.put("freshTea", freshTeaSum);
        salesVolume.put("cheeseTea", cheeseTeaSum);
        return ServerResponse.createBySuccess(salesVolume);
    }

    @Override
    public ServerResponse barChart(String createYearStr) {
        int[] salesVolume = new int[12];
        Timestamp createMonth;
        Timestamp afterCreateMonth;
        for (int i = 0; i < 12; i++) {
            if (i == 11) {
                createMonth = Timestamp.valueOf(createYearStr + "-" + (i + 1) + "-01 00:00:00");
                afterCreateMonth = Timestamp.valueOf((Integer.valueOf(createYearStr) + 1) + "-01-01 00:00:00");
            } else {
                createMonth = Timestamp.valueOf(createYearStr + "-" + (i + 1) + "-01 00:00:00");
                afterCreateMonth = Timestamp.valueOf(createYearStr + "-" + (i + 2) + "-01 00:00:00");
            }
            List<OrderInfo> orderInfoList = orderInfoRepository.findByCreateTimeBetween(createMonth, afterCreateMonth);
            for (int j = 0; j < orderInfoList.size(); j++) {
                List<OrderGood> orderGoods = orderGoodRepository.findByOrderId(orderInfoList.get(j).getId());
                for (int k = 0; k < orderGoods.size(); k++){
                    salesVolume[i] += orderGoods.get(k).getSum();
                }
            }
        }
        return ServerResponse.createBySuccess(salesVolume);
    }

    @Override
    public ServerResponse pieChart(String createYearStr) {
        int[] yearSalesVolume = new int[4];
        Timestamp createYear = Timestamp.valueOf(createYearStr + "-01-01 00:00:00");
        Timestamp afterCreateYear = Timestamp.valueOf((Integer.valueOf(createYearStr) + 1) + "-01-01 00:00:00");
        List<OrderInfo> orderInfoList = orderInfoRepository.findByCreateTimeBetween(createYear, afterCreateYear);
        for (int j = 0; j < orderInfoList.size(); j++) {
            List<OrderGood> orderGoods = orderGoodRepository.findByOrderId(orderInfoList.get(j).getId());
            for (int k = 0; k < orderGoods.size(); k++) {
                int classify = goodRepository.findById(orderGoods.get(k).getGoodId()).getClassify();
                switch (classify) {
                    case 0:
                        yearSalesVolume[0] += orderGoods.get(k).getSum();
                        break;
                    case 1:
                        yearSalesVolume[1] += orderGoods.get(k).getSum();
                        break;
                    case 2:
                        yearSalesVolume[2] += orderGoods.get(k).getSum();
                        break;
                    case 3:
                        yearSalesVolume[3] += orderGoods.get(k).getSum();
                        break;
                }
            }
        }
        return ServerResponse.createBySuccess(yearSalesVolume);
    }
}
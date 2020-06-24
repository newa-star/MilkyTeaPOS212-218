package com.milkyteapos.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.Good;
import com.milkyteapos.dataobject.OrderGood;
import com.milkyteapos.dataobject.OrderInfo;
import com.milkyteapos.dataobject.User;
import com.milkyteapos.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IGoodService iGoodService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IOrderGoodService iOrderGoodService;
    @Autowired
    private IFormService iFormService;

    @GetMapping(value = "/hello")
    public String Hello(){
        return "Hello Spring Boot";
    }

    @PostMapping(value = "/apply")
    public ServerResponse apply(@RequestBody User user){
        return iUserService.applyAccount(user);
    }

    @PostMapping(value = "/review")
    public ServerResponse review(@RequestBody JSONObject jsonObject){
        String code = jsonObject.getString("code");
        int result = jsonObject.getIntValue("result");
        return iUserService.reviewAdmin(code, result);
    }

    @PostMapping(value = "/login")
    public ServerResponse login(@RequestBody User user){
        return iUserService.login(user);
    }

    @PostMapping(value = "/checkPassword")
    public ServerResponse checkPassword(@RequestBody JSONObject jsonObject){
        String code = jsonObject.getString("code");
        String oldPassword = jsonObject.getString("oldPassword");
        return iUserService.checkPassword(code, oldPassword);
    }

    @PostMapping(value = "/updatePassword")
    public ServerResponse updatePassword(@RequestBody JSONObject jsonObject){
        String code = jsonObject.getString("code");
        String newPassword = jsonObject.getString("newPassword");
        return iUserService.updatePassword(code, newPassword);
    }

    @PostMapping(value = "/addGood")
    public ServerResponse addGood(@RequestBody Good good) throws Exception {
        return iGoodService.addGood(good);
    }

    @GetMapping(value = "/findAllGood")
    public ServerResponse findAllGood(){
        return iGoodService.findAllGood();
    }

    @PostMapping(value = "/findGoodByClassify")
    public ServerResponse findGoodByClassify(@RequestBody JSONObject jsonObject){
        int classify = jsonObject.getIntValue("classify");
        return iGoodService.findByClassify(classify);
    }

    @PostMapping(value = "/findGoodByKey")
    public ServerResponse findGoodByKey(@RequestBody JSONObject jsonObject){
        String key = jsonObject.getString("key");
        return iGoodService.findByKey(key);
    }

    @PostMapping(value = "/updateGood")
    public ServerResponse updateGood(@RequestBody Good good) throws IOException {
        return iGoodService.updateGood(good);
    }

    @PostMapping(value = "/deleteGood")
    public ServerResponse deleteGood(@RequestBody JSONObject jsonObject) throws IOException {
        int goodId = jsonObject.getIntValue("id");
        return iGoodService.deleteGood(goodId);
    }

    @PostMapping(value = "/addOrderAndOrderGood")
    public ServerResponse addOrderAndOrderGood(@RequestBody JSONObject jsonObject){
        int userId = jsonObject.getIntValue("userId");
        double totalPrice = jsonObject.getDouble("totalPrice");
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        ServerResponse serverResponse = iOrderService.addOrder(userId, currentTime, totalPrice);
        JSONArray jsonArray = jsonObject.getJSONArray("orderGood");
        List<OrderGood> orderGoodList = (List<OrderGood>) JSONObject.parseArray(jsonArray.toJSONString(), OrderGood.class);
        OrderInfo orderInfo = (OrderInfo) serverResponse.getData();
        int orderId = orderInfo.getId();
        iOrderGoodService.addOrderGood(orderId, orderGoodList);
        return ServerResponse.createBySuccessMessage("订单添加成功");
    }

    @GetMapping(value = "/findAllOrderInfo")
    public ServerResponse findAllOrderInfo(){
        return iOrderService.findAllOrderInfo();
    }

    @PostMapping(value = "/findOrderInfoByUserId")
    public ServerResponse findOrderInfoByUserId(@RequestBody JSONObject jsonObject){
        int userId = jsonObject.getIntValue("userId");
        return iOrderService.findOrderInfoByUserId(userId);
    }

    @RequestMapping(value = "/orderDetail")
    public ServerResponse orderDetail(@RequestBody JSONObject jsonObject){
        int orderId = jsonObject.getIntValue("orderId");
        return iOrderGoodService.findOrderDetail(orderId);
    }

    @RequestMapping(value = "/lineChart")
    public ServerResponse lineChart(@RequestBody JSONObject jsonObject){
        String createYearStr = jsonObject.getString("createYear");
        return iFormService.lineChart(createYearStr);
    }

    @RequestMapping(value = "/barChart")
    public ServerResponse barChart(@RequestBody JSONObject jsonObject){
        String createYearStr = jsonObject.getString("createYear");
        return iFormService.barChart(createYearStr);
    }
    @RequestMapping(value = "/pieChart")
    public ServerResponse pieChart(@RequestBody JSONObject jsonObject){
        String createYearStr = jsonObject.getString("createYear");
        return iFormService.pieChart(createYearStr);
    }
}
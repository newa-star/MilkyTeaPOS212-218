package com.milkyteapos.controller;

import com.alibaba.fastjson.JSONObject;
import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.Good;
import com.milkyteapos.dataobject.User;
import com.milkyteapos.service.IGoodService;
import com.milkyteapos.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IGoodService iGoodService;

    @GetMapping(value = "/hello")
    public String Hello(){
        return "Hello Spring Boot";
    }

    @PostMapping(value = "/apply")
    public ServerResponse apply(@RequestBody User user){
        return iUserService.applyAccount(user);
    }

    @PostMapping(value = "/login")
    public ServerResponse login(@RequestBody JSONObject jsonObject){
        String userName = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        return iUserService.login(user);
    }

    @PostMapping(value = "/addGood")
    public ServerResponse addGood(@RequestBody Good good){
        return iGoodService.addGood(good);
    }

    @GetMapping(value = "/findAllGood")
    public ServerResponse findAllGood(){
        return iGoodService.findAllGood();
    }
}
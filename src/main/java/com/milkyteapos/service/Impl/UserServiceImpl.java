package com.milkyteapos.service.Impl;

import com.milkyteapos.common.Const;
import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.User;
import com.milkyteapos.repository.UserRepository;
import com.milkyteapos.service.IUserService;
import com.milkyteapos.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ServerResponse applyAccount(User user) {
//        user.setToken(UUID.randomUUID().toString().replaceAll("-",""));
//        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
//        userRepository.save(user);
//        User user1 = userRepository.findByToken(user.getToken());
//        if(user1 == null)
//            return ServerResponse.createByErrorMessage("请重新登录");
//        return ServerResponse.createBySuccess(user);
        return null;
    }

    @Override
    public ServerResponse login(User user) {
//        User user1 = userRepository.findByUserName(user.getUserName());
//        if (! MD5Util.MD5EncodeUtf8(user.getPassword()).equals(user1.getPassword())){
//            return ServerResponse.createByErrorMessage("密码错误！");
//        }
//        user1.setToken(UUID.randomUUID().toString().replaceAll("-",""));
//        userRepository.save(user1);
//        return ServerResponse.createBySuccess(user1);
        return null;
    }
}

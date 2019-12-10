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
        User user1 = userRepository.findByCode(user.getCode());
        if(user1 != null){
            return ServerResponse.createByErrorMessage("用户名已存在！");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        userRepository.save(user);
//        User user1 = userRepository.findByToken(user.getToken());
//        if(user1 == null)
//            return ServerResponse.createByErrorMessage("请重新登录");
        return ServerResponse.createBySuccess("注册成功，请登录系统！");
//        return null;
    }

    @Override
    public ServerResponse login(User user) {
        if(user == null){
            return ServerResponse.createByErrorMessage("请输入正确信息！");
        }
        User user1 = userRepository.findByCode(user.getCode());
        if(user1 == null){
            return ServerResponse.createByErrorMessage("账号不存在！");
        }
        if (! MD5Util.MD5EncodeUtf8(user.getPassword()).equals(user1.getPassword())){
            return ServerResponse.createByErrorMessage("密码错误！");
        }
//        user1.setToken(UUID.randomUUID().toString().replaceAll("-",""));
//        userRepository.save(user1);
        user1.setPassword(null);
        return ServerResponse.createBySuccess(user1);
//        return null;
    }

    @Override
    public ServerResponse checkPassword(String code, String oldPassword) {
        User user = userRepository.findByCode(code);
        if(!MD5Util.MD5EncodeUtf8(oldPassword).equals(user.getPassword())){
            return ServerResponse.createByErrorMessage("原密码错误！");
        }
        return ServerResponse.createBySuccessMessage("验证成功！");
    }

    @Override
    public ServerResponse updatePassword(String code, String newPassword) {
        User user = userRepository.findByCode(code);
        if(code == null || newPassword == null){
            return ServerResponse.createByErrorMessage("请输入完整信息！");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
        userRepository.save(user);
        user.setPassword(null);
        return ServerResponse.createBySuccess(user);
    }
}

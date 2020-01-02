package com.milkyteapos.service.Impl;

import com.milkyteapos.common.Const;
import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.ApplyAdmin;
import com.milkyteapos.dataobject.User;
import com.milkyteapos.repository.ApplyAdminRepository;
import com.milkyteapos.repository.UserRepository;
import com.milkyteapos.service.IUserService;
import com.milkyteapos.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplyAdminRepository applyAdminRepository;

    @Override
    public ServerResponse applyAccount(User user) {
//        user.setToken(UUID.randomUUID().toString().replaceAll("-",""));
        User user1 = userRepository.findByCode(user.getCode());
        if(user1 != null){
            return ServerResponse.createByErrorMessage("用户名已存在！");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        if(user.getIdentity() == 0){
            if (applyAdminRepository.findApplyAdminByCode(user.getCode()) != null){
                ApplyAdmin applyAdmin = applyAdminRepository.findApplyAdminByCode(user.getCode());
                applyAdmin.setPassword(null);
                Map map = new LinkedHashMap<>();
                map.put("applyAdmin", applyAdmin);
                map.put("msg", "请求已存在，请勿重复提交！");
                return ServerResponse.createBySuccess(map);
            }
            ApplyAdmin applyAdmin = new ApplyAdmin();
            applyAdmin.setCode(user.getCode());
            applyAdmin.setUserName(user.getUserName());
            applyAdmin.setPassword(user.getPassword());
            applyAdmin.setState(2);
            applyAdminRepository.save(applyAdmin);
            applyAdmin = applyAdminRepository.findApplyAdminByCode(applyAdmin.getCode());
            applyAdmin.setPassword(null);
            Map map = new LinkedHashMap<>();
            map.put("applyAdmin", applyAdmin);
            map.put("msg", "提交成功，请等待管理员审核！");
            return ServerResponse.createBySuccess(map);
        }
        else
            userRepository.save(user);
//        User user1 = userRepository.findByToken(user.getToken());
//        if(user1 == null)
//            return ServerResponse.createByErrorMessage("请重新登录");
        return ServerResponse.createBySuccessMessage("注册成功，请登录系统！");
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
    public ServerResponse reviewAdmin(String code, int result) {
        ApplyAdmin applyAdmin = applyAdminRepository.findApplyAdminByCode(code);
        if(result == 0){
            User user = new User();
            user.setCode(applyAdmin.getCode());
            user.setUserName(applyAdmin.getUserName());
            user.setPassword(applyAdmin.getPassword());
            user.setIdentity(0);
            applyAdmin.setState(0);
            applyAdminRepository.save(applyAdmin);
            return ServerResponse.createBySuccess(userRepository.save(user));
        }
        if(result == 1){
            applyAdmin.setState(1);
            return ServerResponse.createBySuccess(applyAdminRepository.save(applyAdmin));
        }
        return null;
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

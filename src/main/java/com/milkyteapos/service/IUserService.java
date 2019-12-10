package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.User;

public interface IUserService {
    ServerResponse applyAccount(User user);
    ServerResponse login(User user);
    ServerResponse checkPassword(String code, String oldPassword);
    ServerResponse updatePassword(String code, String newPassword);
}

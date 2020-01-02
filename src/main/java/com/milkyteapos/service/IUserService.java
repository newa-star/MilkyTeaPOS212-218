package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.User;

public interface IUserService {
    ServerResponse applyAccount(User user);
    ServerResponse login(User user);
    ServerResponse reviewAdmin(String code, int result);
    ServerResponse checkPassword(String code, String oldPassword);
    ServerResponse updatePassword(String code, String newPassword);
}

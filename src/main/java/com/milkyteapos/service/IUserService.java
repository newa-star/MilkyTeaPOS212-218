package com.milkyteapos.service;

import com.milkyteapos.common.ServerResponse;
import com.milkyteapos.dataobject.User;

public interface IUserService {
    ServerResponse applyAccount(User user);
    ServerResponse login(User user);
}

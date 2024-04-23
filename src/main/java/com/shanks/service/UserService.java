package com.shanks.service;

import com.shanks.model.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail (String email) throws Exception;

}

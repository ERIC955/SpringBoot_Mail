package com.eric.springbootmall.service.impl;


import com.eric.springbootmall.dao.UserDao;
import com.eric.springbootmall.dto.UserRegisterRequest;
import com.eric.springbootmall.model.User;
import com.eric.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        //檢查註冊email
        User user =userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null){
            String errorMessage = "該email " + userRegisterRequest.getEmail() + " 已經被註冊";
            log.warn(errorMessage);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errorMessage);
        }

        return userDao.creatUser(userRegisterRequest);
    }

}

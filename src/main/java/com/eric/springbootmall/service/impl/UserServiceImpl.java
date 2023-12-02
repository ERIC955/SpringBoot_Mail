package com.eric.springbootmall.service.impl;


import com.eric.springbootmall.dao.UserDao;
import com.eric.springbootmall.dto.UserRegisterRequest;
import com.eric.springbootmall.dto.UserloginRequest;
import com.eric.springbootmall.model.User;
import com.eric.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
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
        //使用MD5加密
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        //創建帳號
        return userDao.creatUser(userRegisterRequest);
    }

    @Override
    public User login(UserloginRequest userloginRequest) {
        User user = userDao.getUserByEmail(userloginRequest.getEmail());
        //檢查user是否存在
        if(user == null){
            log.warn("該 email {} 並未註冊",userloginRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(userloginRequest.getPassword().getBytes());

        //比較密碼
        if(user.getPassword().equals(hashedPassword)){
            return user;
        }else {
            log.warn("email密碼不正確",userloginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }


}

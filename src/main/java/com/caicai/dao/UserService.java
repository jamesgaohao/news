package com.caicai.dao;


import com.baomidou.mybatisplus.extension.service.IService;
import com.caicai.entity.user.User;

public interface UserService extends IService<User> {

    User getInfo(String id);
    User getByName(String Name);
    void create(User entity);
    boolean update(String id, User entity);
}

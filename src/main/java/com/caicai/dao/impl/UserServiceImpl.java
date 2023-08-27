package com.caicai.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caicai.dao.UserService;
import com.caicai.entity.user.User;
import com.caicai.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    public User getInfo(String id){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId, id);
        return this.getOne(queryWrapper);

    }

    @Override
    public User getByName(String Name) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserName, Name);
        return this.getOne(queryWrapper);

    }

    @Override
    public void create(User entity) {
       this.save(entity);
    }

    @Override
    public boolean update(String id, User entity) {

        return  this.update(id,entity);
    }


}

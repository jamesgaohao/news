package com.caicai.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caicai.dao.UserReadNewsRecordService;
import com.caicai.entity.userReadNewsRecord.UserReadNewsRecord;
import com.caicai.mapper.UserReadNewsRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReadNewsRecordServiceImpl extends ServiceImpl<UserReadNewsRecordMapper, UserReadNewsRecord> implements UserReadNewsRecordService {
    @Autowired
    private  UserReadNewsRecordMapper userReadNewsRecordMapper;
    @Override
    public List<String>  getUserReadType(String userId) {

        List<String>   types =  userReadNewsRecordMapper.getUserReadType(userId);

        return types;
    }
}

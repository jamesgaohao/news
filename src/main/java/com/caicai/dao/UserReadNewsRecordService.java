package com.caicai.dao;


import com.baomidou.mybatisplus.extension.service.IService;
import com.caicai.entity.userReadNewsRecord.UserReadNewsRecord;

import java.util.List;

public interface UserReadNewsRecordService extends IService<UserReadNewsRecord> {


    List<String> getUserReadType(String userId);
}

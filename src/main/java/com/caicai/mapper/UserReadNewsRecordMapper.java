package com.caicai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caicai.entity.userReadNewsRecord.UserReadNewsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserReadNewsRecordMapper extends BaseMapper<UserReadNewsRecord> {


    List<String>  getUserReadType(@Param("userId") String userId);
}

package com.caicai.util;

import com.alibaba.fastjson.JSONObject;
import com.caicai.dao.UserService;
import com.caicai.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetCurrentUserUtil {
    @Autowired
    private UserService userService;
    public User getCurrentUserInfo(String username){
        User res = null;

        // 获取当前登录用户信息
        try {
            Subject subject = SecurityUtils.getSubject();
            Object userObj = subject.getSession().getAttribute("user");

            if(userObj!=null){
                User  user = JSONObject.parseObject(JSONObject.toJSONString(userObj), User.class);
                res  = userService.getByName(user.getUserName());
            }
            if(res==null){
                if(StringUtils.isNoneBlank(username)){
                    res = userService.getByName(username);
                }
            }

        }catch (Exception e){

        }
        return res;
    }




}

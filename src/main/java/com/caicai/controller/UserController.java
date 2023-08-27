package com.caicai.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caicai.common.ActionResult;
import com.caicai.dao.SubScriptTypeService;
import com.caicai.dao.UserService;
import com.caicai.entity.subcribetype.SubscribeType;
import com.caicai.entity.user.User;
import com.caicai.model.UserModel;
import com.caicai.util.GetCurrentUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private GetCurrentUserUtil getCurrentUserUtil;

    @Autowired
    private SubScriptTypeService subScriptTypeService;


    /***
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public ActionResult updateUser( @RequestBody UserModel user) {
        User userInfo   = getCurrentUserUtil.getCurrentUserInfo(null);
        if(userInfo==null){
            userInfo = userService.getByName(user.getUserName());
            if(userInfo==null){
                return ActionResult.fail("当前用户未登录！！");
            }
        }
        if(user.getSourcePassword()!=null&&StringUtils.isNoneBlank(user.getSourcePassword())&&user.getPassWord()!=null&&StringUtils.isNoneBlank(user.getPassWord())){
            // 判断一用户原密码是否输入正确
            if(!user.getSourcePassword().equals(userInfo.getPassWord())){
                return ActionResult.fail("原密码是否输入有误");
            }
        }

        User userEntity   = JSON.parseObject(JSONObject.toJSONString(user), User.class);
        userEntity.setId(userInfo.getId());
        boolean res = userService.updateById(userEntity);
      return ActionResult.success(res);
    }

    /**
     * 更新订阅结果
     * @param user
     * @return
     */
    @PostMapping("/subscribeUpdate")
    public ActionResult subscribeUpdate( @RequestBody UserModel user) {
        User userEntity   = getCurrentUserUtil.getCurrentUserInfo(null);
        if(userEntity==null){
            userEntity = userService.getByName(user.getUserName());
            if(userEntity==null){
                return ActionResult.fail("当前用户未登录！！");
            }
        }
        userEntity.setSubscribe(user.getSubscribe());
        boolean res = userService.updateById(userEntity);
        return ActionResult.success(res);
    }

    /**
     * 获取所有的订阅类型
     *
     * @return
     */
    @GetMapping("/subscribe/getList")
    public ActionResult getList() {
         List<SubscribeType> list = subScriptTypeService.list();
        return ActionResult.success(list);
    }

    /**
     * 获取当前用户的订阅
     * @return
     */

    @GetMapping("/subscribe/getUserSub")
    public ActionResult getUserSub(@RequestParam("name") String name) {
        User userEntity   = userService.getByName(name);
        if(userEntity==null){
            return ActionResult.fail("没有查询到该用户");
        }
        Map<String,Object> res =new HashMap<>();
        String[] subs = new String[0];
        if(StringUtils.isNoneBlank(userEntity.getSubscribe())){
            subs = userEntity.getSubscribe().split(",");
        }
        res.put("sub",subs);
        return ActionResult.success(res);
    }


}

package com.caicai.controller;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.caicai.common.ActionResult;
import com.caicai.dao.NewsService;
import com.caicai.dao.UserService;
import com.caicai.entity.user.User;
import com.caicai.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ActionResult login(@RequestBody User user) {
        // 调用Shiro的登录方法进行身份验证
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        try {
            subject.login(token);
            return ActionResult.success("登录成功！",token);
        } catch (AuthenticationException e) {
            return ActionResult.fail("登录密码错误！");
        }
    }

    @GetMapping("/checkLogin")
    public ActionResult checkLogin() {
        User user = null;
        Subject subject = SecurityUtils.getSubject();
        Object userObj = subject.getSession().getAttribute("user");
        if(userObj!=null){
            user = JSONObject.parseObject(JSONObject.toJSONString(userObj),User.class);
        }
        if(user!=null){
            return ActionResult.success("用户已登录");
        }
        return ActionResult.fail("用户未登录");
    }




    /**
     *
     * @return
     */

   @GetMapping("/getList")
    public ActionResult queryAll() {
        // 调用Shiro的登录方法进行身份验证
        List<User> list = userService.list();
        return ActionResult.success(list);
   }



    @PostMapping("/Register")
    public ActionResult Register(@RequestBody UserModel user) {
       log.info("com.caicai.controller.LoginController.Register==>入参{}",user);
           User userEntity   = JSON.parseObject(JSONObject.toJSONString(user), User.class);
           userEntity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
           userEntity.setCreateTime(new Date());
           userEntity.setCreateBy("System");
            User userRes = userService.getByName(user.getUserName());
            if(userRes!=null){
                return ActionResult.fail("用户已经存在！");
            }
        boolean save = userService.save(userEntity);
        return ActionResult.success(save);
    }




}

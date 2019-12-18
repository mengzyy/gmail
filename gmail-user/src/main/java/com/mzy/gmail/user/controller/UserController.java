package com.mzy.gmail.user.controller;

import com.mzy.gmail.user.bean.UmsMember;
import com.mzy.gmail.user.bean.UmsMemberReceiveAddress;
import com.mzy.gmail.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class UserController {


    @Autowired
    UserService userService;



    @RequestMapping("index")
    @ResponseBody
    public String test(){

        return "hello gmail";

    }



    //测试获取用户表的全部信息
    @RequestMapping("getAlluser")
    @ResponseBody
    public List<UmsMember> getAlluser(){


        List<UmsMember> userMembers= userService.getAlluser();
        return userMembers;

    }

    //测试获取用户的地址
    @RequestMapping("getUserAddress")
    @ResponseBody
    public List<UmsMemberReceiveAddress> getUserAddress(Long memberid){


        List<UmsMemberReceiveAddress> userMemberAddresss= userService.getUserAddress(memberid);
        return userMemberAddresss;

    }





}

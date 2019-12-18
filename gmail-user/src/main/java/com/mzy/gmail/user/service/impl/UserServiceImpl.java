package com.mzy.gmail.user.service.impl;

import com.mzy.gmail.user.bean.UmsMember;
import com.mzy.gmail.user.bean.UmsMemberReceiveAddress;
import com.mzy.gmail.user.mapper.UmsMemberReceiveAddressMapper;
import com.mzy.gmail.user.mapper.UserMapper;
import com.mzy.gmail.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;


    @Override
    public List<UmsMember> getAlluser() {

        List<UmsMember> userMembers = userMapper.selectAllUser();

        return userMembers;
    }

    @Override
    public List<UmsMemberReceiveAddress> getUserAddress(Long member) {


        UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(member);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddressList=umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);
        return umsMemberReceiveAddressList;
    }
}

package com.mzy.gmail.user.service;

import com.mzy.gmail.user.bean.UmsMember;
import com.mzy.gmail.user.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAlluser();

    List<UmsMemberReceiveAddress> getUserAddress(Long member);


}

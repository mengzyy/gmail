package com.mzy.gmall.service;

import com.mzy.gmall.bean.UmsMember;
import com.mzy.gmall.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {
    List<UmsMember> getAlluser();

    List<UmsMemberReceiveAddress> getUserAddress(Long member);


}

package com.mzy.gmail.user.mapper;


import com.mzy.gmail.user.bean.UmsMember;
import tk.mybatis.mapper.common.Mapper;
//import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<UmsMember> {
    List<UmsMember> selectAllUser();

}

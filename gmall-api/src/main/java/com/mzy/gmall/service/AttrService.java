package com.mzy.gmall.service;

import com.mzy.gmall.bean.PmsBaseAttrInfo;
import com.mzy.gmall.bean.PmsBaseAttrValue;
import com.mzy.gmall.bean.PmsBaseSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface AttrService  {
    List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    void saveAttrInfo(PmsBaseAttrInfo pmsbaseattinfo);

    List<PmsBaseSaleAttr> getBaseSaleAttrList();

    List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet);
}

package com.mzy.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mzy.gmall.bean.PmsBaseAttrInfo;
import com.mzy.gmall.bean.PmsBaseAttrValue;
import com.mzy.gmall.bean.PmsBaseSaleAttr;
import com.mzy.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.mzy.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.mzy.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.mzy.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class AttrServiceImpl implements AttrService {
    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

    @Override
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);
        //这边没有封装
        for (PmsBaseAttrInfo baseAttrInfo : pmsBaseAttrInfoList) {
            List<PmsBaseAttrValue> pmsBaseAttrValueList = new ArrayList<PmsBaseAttrValue>();

            PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            pmsBaseAttrValueList=pmsBaseAttrValueMapper.select(pmsBaseAttrValue);

            baseAttrInfo.setAttrValueList(pmsBaseAttrValueList);


        }





        return pmsBaseAttrInfoList;


    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
        return pmsBaseAttrValueList;
    }

    @Override
    public void saveAttrInfo(PmsBaseAttrInfo pmsbaseattinfo) {
        String id = pmsbaseattinfo.getId();

        if(StringUtils.isBlank(id)){
            //如果为空，则为插入操作
            List<PmsBaseAttrValue> pmsBaseAttrValueList = pmsbaseattinfo.getAttrValueList();
            //保存属性
            pmsBaseAttrInfoMapper.insertSelective(pmsbaseattinfo);
            //保存属性值
            for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrValueList) {
                pmsBaseAttrValue.setAttrId(pmsbaseattinfo.getId());
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);

            }



        }else{

            //修改info
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsbaseattinfo.getId());
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsbaseattinfo,example);

            //修改value
            //先进行删除
            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(id);
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);
            //在进行插入
            List<PmsBaseAttrValue> pmsBaseAttrValueList=pmsbaseattinfo.getAttrValueList();
            for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrValueList) {
                pmsBaseAttrValue.setId(null);
                pmsBaseAttrValue.setAttrId(id);
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }








        }




    }

    @Override
    public List<PmsBaseSaleAttr> getBaseSaleAttrList() {
        List<PmsBaseSaleAttr> pmsBaseSaleAttrList = pmsBaseSaleAttrMapper.selectAll();
        return pmsBaseSaleAttrList;



    }

    @Override
    public List<PmsBaseAttrInfo> getAttrValueListByValueId(Set<String> valueIdSet) {
        String valueIdStr = StringUtils.join(valueIdSet, ",");//41,45,46
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.selectAttrValueListByValueId(valueIdStr);
        return pmsBaseAttrInfos;


    }


}

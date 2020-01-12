package com.mzy.gmall.manage.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.mzy.gmall.bean.*;
import com.mzy.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.mzy.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.mzy.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.mzy.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;

    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;

    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;


    @Override
    public List<PmsBaseCatalog1> getCatalog1() {

        //获取catalog全部信息
        List<PmsBaseCatalog1> pmsBaseCatalog1List = pmsBaseCatalog1Mapper.selectAll();
        return pmsBaseCatalog1List;
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {


        Example example = new Example(PmsBaseCatalog2.class);
        example.createCriteria().andEqualTo("catalog1Id", catalog1Id);
        List<PmsBaseCatalog2> pmsBaseCatalog2List = pmsBaseCatalog2Mapper.selectByExample(example);
        return pmsBaseCatalog2List;
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {

//        PmsBaseCatalog3 pmsBaseCatalog3 = new PmsBaseCatalog3();
//        pmsBaseCatalog3.setCatalog2Id(catalog2Id);
//        List<PmsBaseCatalog3> pmsBaseCatalog3List = pmsBaseCatalog3Mapper.select(pmsBaseCatalog3);
//        return pmsBaseCatalog3List;

        Example example = new Example(PmsBaseCatalog3.class);
        example.createCriteria().andEqualTo("catalog2Id", catalog2Id);
        List<PmsBaseCatalog3> pmsBaseCatalog3List = pmsBaseCatalog3Mapper.selectByExample(example);
        return pmsBaseCatalog3List;

    }
}

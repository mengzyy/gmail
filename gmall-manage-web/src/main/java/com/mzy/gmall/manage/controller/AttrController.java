package com.mzy.gmall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mzy.gmall.bean.PmsBaseAttrInfo;
import com.mzy.gmall.bean.PmsBaseAttrValue;
import com.mzy.gmall.bean.PmsBaseSaleAttr;
import com.mzy.gmall.service.AttrService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class AttrController {

    @Reference
    AttrService attrService;

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<PmsBaseAttrInfo> getAttrInfoList(String catalog3Id) {


        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = attrService.getAttrInfoList(catalog3Id);
        return pmsBaseAttrInfoList;


    }

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {


        List<PmsBaseAttrValue> pmsBaseAttrValueList = attrService.getAttrValueList(attrId);
        return pmsBaseAttrValueList;


    }


    @RequestMapping("saveAttrInfo")
    @ResponseBody
    public void getAttrValueList(@RequestBody PmsBaseAttrInfo pmsbaseattinfo) {


        attrService.saveAttrInfo(pmsbaseattinfo);


    }

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public  List<PmsBaseSaleAttr> getBaseSaleAttrList() {

        List<PmsBaseSaleAttr> pmsBaseSaleAttrList = attrService.getBaseSaleAttrList();

        return pmsBaseSaleAttrList;


    }


}

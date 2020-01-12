package com.mzy.gmall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mzy.gmall.bean.PmsProductInfo;
import com.mzy.gmall.bean.PmsSkuInfo;
import com.mzy.gmall.service.PmsSkuService;
import com.mzy.gmall.service.PmsSpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
public class SkuController {

    @Reference
    PmsSkuService pmsSkuService;

    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String  saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {

        pmsSkuService.saveSkuInfo(pmsSkuInfo);


        return "success";


    }





}

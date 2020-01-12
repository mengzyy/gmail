package com.mzy.gmall.manage.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mzy.gmall.bean.PmsProductImage;
import com.mzy.gmall.bean.PmsProductInfo;
import com.mzy.gmall.bean.PmsProductSaleAttr;
import com.mzy.gmall.manage.util.PmsUploadUtil;
import com.mzy.gmall.service.PmsSpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {


    @Reference
    PmsSpuService pmsSpuService;


    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> getSpuList(String catalog3Id) {

        List<PmsProductInfo> pmsProductInfoList = pmsSpuService.getSpuList(catalog3Id);

        return pmsProductInfoList;


    }


    //上传图片
    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        // 将图片或者音视频上传到分布式的文件存储系统
        // 将图片的存储路径返回给页面
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        System.out.println(imgUrl);
        return imgUrl;
    }


    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {

        pmsSpuService.saveSpuInfo(pmsProductInfo);
        return "success";


    }


    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId) {

        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsSpuService.getSpuSaleAttrList(spuId);

        return pmsProductSaleAttrList;


    }


    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> getSpuImageList(String spuId) {

        List<PmsProductImage> pmsProductSaleAttrList = pmsSpuService.getSpuImageList(spuId);

        return pmsProductSaleAttrList;


    }





}

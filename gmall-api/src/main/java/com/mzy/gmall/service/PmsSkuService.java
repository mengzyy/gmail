package com.mzy.gmall.service;

import com.mzy.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface PmsSkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId);


    List<PmsSkuInfo> getAllSkuInfo();

}

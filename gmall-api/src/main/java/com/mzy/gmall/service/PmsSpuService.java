package com.mzy.gmall.service;

import com.mzy.gmall.bean.PmsProductImage;
import com.mzy.gmall.bean.PmsProductInfo;
import com.mzy.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface PmsSpuService {


    List<PmsProductInfo> getSpuList(String catalog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> getSpuSaleAttrList(String spuId);

    List<PmsProductImage> getSpuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId, String id);
}

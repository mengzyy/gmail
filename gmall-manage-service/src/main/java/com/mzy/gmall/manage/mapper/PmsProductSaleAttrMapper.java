package com.mzy.gmall.manage.mapper;

import com.mzy.gmall.bean.PmsProductSaleAttr;
import tk.mybatis.mapper.common.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr>{
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId") String skuId);
}

package com.mzy.gmall.service;

import com.mzy.gmall.bean.PmsSearchParam;
import com.mzy.gmall.bean.PmsSearchSkuInfo;

import java.util.List;

public interface SearchService {
    List<PmsSearchSkuInfo> list(PmsSearchParam pmsSearchParam);
}

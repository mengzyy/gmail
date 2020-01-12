package com.mzy.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.mzy.gmall.bean.PmsSkuAttrValue;
import com.mzy.gmall.bean.PmsSkuImage;
import com.mzy.gmall.bean.PmsSkuInfo;
import com.mzy.gmall.bean.PmsSkuSaleAttrValue;
import com.mzy.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.mzy.gmall.manage.mapper.PmsSkuImageMapper;
import com.mzy.gmall.manage.mapper.PmsSkuInfoMapper;
import com.mzy.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.mzy.gmall.service.PmsSkuService;
import com.mzy.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PmsSkuServiceImpl implements PmsSkuService {
    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;
    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;
    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedissonClient redissonClient;


    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());
        //PmsSkuInfo加入
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);

        //得到skuid
        String skuid = pmsSkuInfo.getId();
        //这个是sku列表
        List<PmsSkuImage> pmsSkuImageList = pmsSkuInfo.getSkuImageList();
        for (PmsSkuImage pmsSkuImage : pmsSkuImageList) {
            pmsSkuImage.setSkuId(skuid);
            pmsSkuImageMapper.insertSelective(pmsSkuImage);
        }
        //平台属性列表
        List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuAttrValueList) {
            pmsSkuAttrValue.setSkuId(skuid);
            pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        //销售属性列表
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuSaleAttrValueList) {
            pmsSkuSaleAttrValue.setSkuId(skuid);
            pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);

        }


    }

    public PmsSkuInfo getSkuByIdFromDb(String skuId) {
        // sku商品对象
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        pmsSkuInfo.setId(skuId);
        PmsSkuInfo skuInfo = pmsSkuInfoMapper.selectOne(pmsSkuInfo);

        // sku的图片集合
        PmsSkuImage pmsSkuImage = new PmsSkuImage();
        pmsSkuImage.setSkuId(skuId);
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(pmsSkuImage);
        skuInfo.setSkuImageList(pmsSkuImages);
        return skuInfo;
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        //res
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        //获取一个redis连接
        Jedis jedis = redisUtil.getJedis();
        //redis的skuid的键
        String keySkuId = "sku:" + skuId + ":info";
        String skuinfoJson = jedis.get(keySkuId);
        if (StringUtils.isNotBlank(skuinfoJson)) {
            //存在该缓存
            pmsSkuInfo = JSON.parseObject(skuinfoJson, PmsSkuInfo.class);
        } else {
            //没有该缓存 ,查数据库
            //查数据库需要授权分布式锁
            String token = UUID.randomUUID().toString();
            String lockRes = jedis.set("sku:" + skuId + ":lock", token, "nx", "px", 60 * 1000);// 拿到锁的线程有10秒的过期时间
            if (StringUtils.isNotBlank(lockRes) && lockRes.equals("OK")) {
                //允许查数据库
                pmsSkuInfo = getSkuByIdFromDb(skuId);
                if (pmsSkuInfo != null) {
                    jedis.set(keySkuId, JSON.toJSONString(pmsSkuInfo));
                } else {
                    //将空值放入,防止缓存穿透
                    jedis.setex(keySkuId, 1000, JSON.toJSONString(""));
                }
                //释放锁
                String tokenValid = jedis.get("sku:" + skuId + ":lock");
                if (StringUtils.isNotBlank(tokenValid) && tokenValid.equals(token)) {
                    System.out.println("dsdssdsd");
                    jedis.del("sku:" + skuId + ":lock");// 用token确认删除的是自己的sku的锁
                }


            } else {
                //如果没有拿到说明别人在使用
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return getSkuById(skuId);


            }


        }
        //关闭redis连接
        jedis.close();

        return pmsSkuInfo;

    }


    @Override
    public List<PmsSkuInfo> getSkuSaleAttrValueListBySpu(String productId) {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);

        return pmsSkuInfos;
    }

    @Override
    public List<PmsSkuInfo> getAllSkuInfo() {
        List<PmsSkuInfo> pmsSkuInfoList =pmsSkuInfoMapper.selectAll();
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList) {
            String skuid = pmsSkuInfo.getId();
            PmsSkuAttrValue pmsSkuAttrValue = new PmsSkuAttrValue();
            pmsSkuAttrValue.setSkuId(skuid);
            List<PmsSkuAttrValue> pmsSkuAttrValueList = pmsSkuAttrValueMapper.select(pmsSkuAttrValue);

            pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValueList);
        }
        return pmsSkuInfoList;


    }


}

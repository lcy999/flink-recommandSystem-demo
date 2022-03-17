package com.demo.service;

import com.demo.dao.HLogDao;
import com.demo.dao.HProdDao;
import com.demo.dao.HUnionProdDao;
import com.demo.dao.HUserDao;
import com.demo.dao.RTopProductDao;
import com.demo.domain.HProdEntity;
import com.demo.domain.HUnionProdEntity;
import com.demo.enums.AgeStageProduct;
import com.demo.enums.PopularStageProduct;
import com.demo.enums.SexStageProduct;
import com.demo.enums.SpeedOrderStageProduct;
import com.demo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lcy
 * @date: 2022/3/11
 **/
@Service
public class HDbService {

    @Autowired
    private HLogDao hLogDao;

    @Autowired
    private HProdDao hProdDao;

    @Autowired
    private HUnionProdDao hUnionProdDao;

    @Autowired
    private HUserDao hUserDao;

    @Autowired
    private RTopProductDao rTopProductDao;


    /**
     * 在用户操作所有商品中，下单每个动作小于100秒的商品占总商品的比率
     * @param userId
     * @return
     */
    public String queryUserSpeedImage(String userId){
        List<HUnionProdEntity> uHistory = hUnionProdDao.selectByIdAndType(userId, "u_history");
        List<HUnionProdEntity> uInterest = hUnionProdDao.selectByIdAndType(userId, "u_interest");
        double rate = uInterest.size() * 1.0 / uHistory.size();
        SpeedOrderStageProduct speedOrderStageProduct = SpeedOrderStageProduct.getSpeedOrderStageProduct(rate);
        if (speedOrderStageProduct == null) {
            return null;
        }
        return speedOrderStageProduct.name();
    }


    /**
     * 在用户操作的所有商品中，包含热销产品数量的比例，在定义用户潮流的标签
     * @param userId
     * @return
     */
    public String queryUserPolularImage(String userId){
        List<HUnionProdEntity> uHistory = hUnionProdDao.selectByIdAndType(userId, "u_history");
        if (uHistory.size() == 0) {
            return null;
        }
        List<String> topProducts = rTopProductDao.selectTopN(Constants.TOP_SIZE + "");
        int count=0;
        for (String pId : topProducts) {
            for (HUnionProdEntity hUnionProdEntity : uHistory) {
                if (pId.equals(hUnionProdEntity.getProduct())) {
                    count++;
                    break;
                }
            }
        }
        double rate = count * 1.0 / topProducts.size();
        PopularStageProduct popularStageProduct = PopularStageProduct.getPopularStageProduct(rate);
        if (popularStageProduct == null) {
            return null;
        }
        return popularStageProduct.name();
    }


    /**
     * 在用户操作的所有商品中，按统计算大多数产品分类成性别标签和年轻标签
     * @param userId
     * @param isAge
     * @return
     */
    public String queryUserAgeOrSexImage(String userId,boolean isAge){
        List<HUnionProdEntity> uHistory = hUnionProdDao.selectByIdAndType(userId, "u_history");
        if (uHistory.size() == 0) {
            return null;
        }
        Map<String, Integer> ageImageCount = new HashMap<>();
        for (HUnionProdEntity item : uHistory) {
            String productId = item.getProduct();
            String imageName = getAgeOrSexImage(productId,isAge);
            Integer lastValue = ageImageCount.getOrDefault(imageName, 0);
            ageImageCount.put(imageName, Integer.parseInt(item.getValue())+lastValue);
        }

        List<Map.Entry<String, Integer>> sortList = new ArrayList<>(ageImageCount.entrySet());


        //排序
        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //return (o2.getValue() - o1.getValue());
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        String key = sortList.get(0).getKey();
        return key;
    }

    public String getAgeOrSexImage(String productId,boolean isAge){
        List<HProdEntity> hProdEntities = hProdDao.selectById(productId);
        Map<String, Integer> imageCount = new HashMap<>();
        for (HProdEntity item : hProdEntities) {
            String key = "";
            if (isAge) {
                Integer age = item.getAge();
                if (age == null) {
                    continue;
                }

                key = AgeStageProduct.getAgeStageProduct(age).name();
            }else{
                String sex = item.getSex();
                if (sex == null) {
                    continue;
                }
                if (sex.equals("1")) {
                    key = SexStageProduct.MAN_PRODUCT.name();
                }else{
                    key = SexStageProduct.WAMAN_PRODUCT.name();
                }
            }

            Integer value = imageCount.getOrDefault(key,0);
            value++;
            imageCount.put(key, value);
        }

        List<Map.Entry<String, Integer>> sortList = new ArrayList<>(imageCount.entrySet());


        //排序
        Collections.sort(sortList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //return (o2.getValue() - o1.getValue());
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });

        String key = sortList.get(0).getKey();
//        return AgeStageProduct.getAgeStageProduct(key);
        return key;
    }


}

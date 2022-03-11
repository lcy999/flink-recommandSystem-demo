package com.demo.service;

import com.demo.dao.HLogDao;
import com.demo.dao.HProdDao;
import com.demo.dao.HUnionProdDao;
import com.demo.dao.HUserDao;
import com.demo.dao.RTopProductDao;
import com.demo.domain.HProdEntity;
import com.demo.domain.HUnionProdEntity;
import com.demo.enums.AgeStageProduct;
import com.demo.enums.SexStageProduct;
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




    public String queryUserAgeOrSexImage(String userId,boolean isAge){
        List<HUnionProdEntity> uHistory = hUnionProdDao.selectByIdAndType(userId, "u_history");
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
                key = AgeStageProduct.getAgeStageProduct(age).name();
            }else{
                String sex = item.getSex();
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

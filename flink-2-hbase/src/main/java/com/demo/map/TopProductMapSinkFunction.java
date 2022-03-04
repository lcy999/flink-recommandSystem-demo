package com.demo.map;

import com.demo.client.MysqlClient;
import com.demo.domain.TopProductEntity;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author XINZE
 */
public class TopProductMapSinkFunction implements MapFunction<TopProductEntity, TopProductEntity> {
    @Override
    public TopProductEntity map(TopProductEntity s) throws Exception {

        String sql = String.format("REPLACE INTO r_top_product VALUES(%s,%s,%s,'%s')",
                s.getProductId(),s.getActionTimes(),s.getWindowEnd(),s.getRankName());

        MysqlClient.executeSql(sql);


        return s;
    }
}

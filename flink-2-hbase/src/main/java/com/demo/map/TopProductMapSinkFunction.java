package com.demo.map;

import com.demo.client.MysqlClient;
import com.demo.domain.TopProductEntity;
import org.apache.flink.api.common.functions.MapFunction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author XINZE
 */
public class TopProductMapSinkFunction implements MapFunction<TopProductEntity, TopProductEntity> {
    @Override
    public TopProductEntity map(TopProductEntity s) throws Exception {


//
        String sql = String.format("Insert INTO r_top_product VALUES(%s,%s,%s,'%s')",
                s.getProductId(),s.getActionTimes(),s.getWindowEnd(),s.getRankName());

        MysqlClient.executeSql(sql);


//        updateData(s.getProductId(), s.getRankName());


        return s;
    }

    private void updateData(int productId, String rankName) throws SQLException {
        String querySql = String.format("select rank_name from r_top_product where product_id='%s'"
                , productId);
        ResultSet resultSet = MysqlClient.querySql(querySql);
        boolean next = resultSet.next();
        String value = "1";
        if (next) {
            value = resultSet.getString("rank_name");
        }
        value = Integer.parseInt(value) + Integer.parseInt(rankName) + "";
        String deleteSql = String.format("delete from r_top_product where product_id='%s'"
                , productId);
        MysqlClient.executeSql(deleteSql);

        String sql = String.format("insert INTO r_top_product VALUES('%s','%s','%s','%s')", productId,"" ,"", value);
        MysqlClient.executeSql(sql);
    }
}

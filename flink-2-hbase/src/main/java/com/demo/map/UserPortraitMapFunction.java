package com.demo.map;

import com.demo.client.HbaseClient;
import com.demo.client.MysqlClient;
import com.demo.domain.LogEntity;
import com.demo.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author XINZE
 */
public class UserPortraitMapFunction implements MapFunction<String, String> {
    @Override
    public String map(String s) throws Exception {
        LogEntity log = LogToEntity.getLog(s);
        ResultSet rst = MysqlClient.selectById(log.getProductId());
        if (rst != null){
            while (rst.next()){
                String userId = String.valueOf(log.getUserId());

                String country = rst.getString("country");
//                HbaseClient.increamColumn("user",userId,"country",country);
                String color = rst.getString("color");
//                HbaseClient.increamColumn("user",userId,"color",color);
                String style = rst.getString("style");
//                HbaseClient.increamColumn("user",userId,"style",style);


                updateData("country", userId, country);
                updateData("color", userId, color);
                updateData("style", userId, style);


            }

        }
        return null;
    }

    private void updateData(String fieldName,String f1, String f2) throws SQLException {
        String querySql=String.format("select value from h_user where id='%s' and %s='%s'",f1,fieldName,f2);
        ResultSet resultSet = MysqlClient.querySql(querySql);
        boolean next = resultSet.next();
        String value = "1";
        if (next) {
            value = resultSet.getString("value");
            value = Integer.parseInt(value) + 1 + "";
        }
        String deleteSql = String.format("delete from h_user where id=%s and %s='%s'", f1, fieldName, f2);
        MysqlClient.executeSql(deleteSql);

        String sql = String.format("insert INTO h_user(id,%s,value) VALUES('%s','%s','%s')",fieldName,f1,f2,value);
        MysqlClient.executeSql(sql);
    }
}

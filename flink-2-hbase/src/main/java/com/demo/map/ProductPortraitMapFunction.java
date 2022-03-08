package com.demo.map;

import com.demo.client.MysqlClient;
import com.demo.domain.LogEntity;
import com.demo.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author XINZE
 */
public class ProductPortraitMapFunction implements MapFunction<String, String> {
    @Override
    public String map(String s) throws Exception {
        LogEntity log = LogToEntity.getLog(s);
        ResultSet rst = MysqlClient.selectUserById(log.getUserId());
//        rst.last();
        if (rst != null){
            while (rst.next()){
                String productId = String.valueOf(log.getProductId());
                String sex = rst.getString("sex");
//                HbaseClient.increamColumn("prod",productId,"sex",sex);
                String age = rst.getString("age");
//                HbaseClient.increamColumn("prod",productId,"age", AgeUtil.getAgeType(age));
                updateData("sex", productId, sex);
                updateData("age", productId, age);

            }
        }
        return null;
    }

    private void updateData(String fieldName,String f1, String f2) throws SQLException {
        String querySql=String.format("select value from h_prod where id='%s' and %s='%s'",f1,fieldName,f2);
        ResultSet resultSet = MysqlClient.querySql(querySql);
        boolean next = resultSet.next();
        String value = "1";
        if (next) {
            value = resultSet.getString("value");
            value = Integer.parseInt(value) + 1 + "";
        }
        String deleteSql = String.format("delete from h_prod where id=%s and %s='%s'", f1, fieldName, f2);
        MysqlClient.executeSql(deleteSql);

        String sql = String.format("insert INTO h_prod(id,%s,value) VALUES('%s','%s','%s')",fieldName,f1,f2,value);
        MysqlClient.executeSql(sql);
    }

    public static void main(String[] args) throws SQLException {
        String querySql=String.format("select value from h_prod where id='%s' and sex='%s'","1001","0");
        ResultSet resultSet = MysqlClient.querySql(querySql);
        boolean next = resultSet.next();
        String value = resultSet.getString(0);
        System.out.println(value);


    }
}

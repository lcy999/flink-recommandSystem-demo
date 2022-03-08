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
public class UserHistoryMapFunction implements MapFunction<String, String> {

    /**
     * 将 用户-产品  和 产品-用户 分别存储Hbase表
     *
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public String map(String s) throws Exception {
        LogEntity log = LogToEntity.getLog(s);
        if (null != log) {
//            HbaseClient.increamColumn("u_history",String.valueOf(log.getUserId()),"p",String.valueOf(log.getProductId()));
//            HbaseClient.increamColumn("p_history",String.valueOf(log.getProductId()),"p",String.valueOf(log.getUserId()));

            updateData(log.getUserId()+"", log.getProductId()+"", "u_history");
            updateData(log.getProductId()+"",log.getUserId()+"",  "p_history");

        }
        return "";
    }

    private void updateData(String id, String productId, String unionType) throws SQLException {
        String querySql = String.format("select value from h_union_prod where id='%s' and product='%s' and union_type='%s'"
                , id, productId, unionType);
        ResultSet resultSet = MysqlClient.querySql(querySql);
        boolean next = resultSet.next();
        String value = "1";
        if (next) {
            value = resultSet.getString("value");
            value = Integer.parseInt(value) + 1 + "";
        }
        String deleteSql = String.format("delete from h_union_prod where id='%s' and product='%s' and union_type='%s'"
                , id, productId, unionType);
        MysqlClient.executeSql(deleteSql);

        String sql = String.format("insert INTO h_union_prod VALUES('%s','%s','%s','%s')", id, productId, unionType, value);
        MysqlClient.executeSql(sql);
    }
}

package com.demo.map;

import com.demo.client.MysqlClient;
import com.demo.domain.LogEntity;
import com.demo.util.LogToEntity;
import org.apache.flink.api.common.functions.MapFunction;

/**
 * @author XINZE
 */
public class LogMapFunction implements MapFunction<String, LogEntity> {

    @Override
    public LogEntity map(String s) throws Exception {
        System.out.println(s);
        LogEntity log = LogToEntity.getLog(s);
        if (null != log){
            String rowKey = log.getUserId() + "_" + log.getProductId()+ "_"+ log.getTime();
            String sql = String.format("REPLACE INTO h_log VALUES('%s','%s','%s','%s','%s')",rowKey,String.valueOf(log.getUserId())
            ,String.valueOf(log.getProductId()),log.getTime().toString(),log.getAction());
            int rows = MysqlClient.executeSql(sql);
            System.out.println(rows);
//            HbaseClient.putData("con",rowKey,"log","userid",String.valueOf(log.getUserId()));
//            HbaseClient.putData("con",rowKey,"log","productid",String.valueOf(log.getProductId()));
//            HbaseClient.putData("con",rowKey,"log","time",log.getTime().toString());
//            HbaseClient.putData("con",rowKey,"log","action",log.getAction());
        }
        return log;
    }

    public static void main(String[] args) {
        String sql = String.format("REPLACE INTO h_log VALUES('%s')", "aaa");
        System.out.println(sql);
    }
}

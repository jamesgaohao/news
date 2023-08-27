//package com.caicai.config.dataSource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
///**
// * 数据源配置
// */
//
//@Configuration
//@Slf4j
//public class DruidConfig {
//
//    @Bean     //声明其为Bean实例
//    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
//    public DataSource dataSource(){
//        DruidDataSource datasource = new DruidDataSource();
//
//        datasource.setUrl("jdbc:mysql://127.0.0.1:3306/SubscribeType?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&allowMultiQueries=true");
//        datasource.setUsername("root");
//        datasource.setPassword("!QAZ2wsx");
//        datasource.setDriverClassName("com.mysql.jdbc.Driver");
//        //configuration
//        datasource.setInitialSize(1);
//        datasource.setMinIdle(5);
//        datasource.setMaxActive(20);
//        datasource.setMaxWait(10);
//        datasource.setTimeBetweenEvictionRunsMillis(60000);
//        datasource.setMinEvictableIdleTimeMillis(60000);
//        datasource.setTestWhileIdle(true);
//        datasource.setTestOnBorrow(false);
//        datasource.setTestOnReturn(false);
//        datasource.setPoolPreparedStatements(true);
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
//        try {
//            datasource.setFilters("stat");
//        } catch (SQLException e) {
//            log.error("druid configuration initialization filter", e);
//        }
//
//        return datasource;
//    }
//
//
//
//
//
//
//
//}

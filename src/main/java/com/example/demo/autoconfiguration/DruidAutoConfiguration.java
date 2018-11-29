package com.example.demo.autoconfiguration;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@EnableConfigurationProperties(DruidAutoConfiguration.DruidProperties.class)
@Configuration
public class DruidAutoConfiguration {

    @Bean
    public DataSource druidDataSource(DataSourceProperties dataSourceProperties, DruidProperties druidProperies) {
        DruidDataSource dataSource = new DruidDataSource();

        setGeneralProperties(dataSource, dataSourceProperties);
        setDruidProperies(dataSource, druidProperies);

        return dataSource;
    }

    private void setGeneralProperties(DruidDataSource dataSource, DataSourceProperties dataSourceProperties) {
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
    }

    private void setDruidProperies(DruidDataSource dataSource, DruidProperties druidProperies) {
        dataSource.setInitialSize(druidProperies.getInitialSize());
        // minPoolSize
        dataSource.setMinIdle(druidProperies.getMinIdle());
        // maxPoolSize
        dataSource.setMaxActive(druidProperies.getMaxActive());
        dataSource.setMaxWait(druidProperies.getMaxWait());

//        dataSource.setTestWhileIdle(true);
//        dataSource.setValidationQuery(druidProperies.getValidationQuery());
        try {
            if (StringUtils.isNotEmpty(druidProperies.getFilters())) {
                dataSource.setFilters(druidProperies.getFilters());
            }
        } catch (SQLException e) {
            log.error("Set filters failed!", e);
        }
        dataSource.setConnectionProperties("druid.stat.logSlowSql=true");
    }

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Setter
    @Getter
    static class DruidProperties {

        private int initialSize = 1;
        private int minIdle = 1;
        private int maxActive = 8;
        private int maxWait = 2000;
        private String filters;
        private String validationQuery;

    }

}

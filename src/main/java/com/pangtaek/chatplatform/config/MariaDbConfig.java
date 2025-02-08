package com.pangtaek.chatplatform.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import jakarta.persistence.EntityManagerFactory;

@Configuration // application.yaml 파일의 값 참조를 허용하는 어노테이션
@EnableTransactionManagement // 트랜잭션 처리를 허용하게 하는 어노테이션
public class MariaDbConfig {

    @Value("${spring.database.url}")
    private String url;

    @Value("${spring.database.username}")
    private String username;

    @Value("${spring.database.password}")
    private String password;

    @Value("${spring.database.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

    @Bean(name = "createUserTransactionManager")
    public PlatformTransactionManager createUserTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource);
        // manager 객체 커스텀

        return manager;
    }

    @Bean(name = "createChatTransactionManager")
    public PlatformTransactionManager createChatTransactionManager(EntityManagerFactory entityManaygerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManaygerFactory);

        return transactionManager;
    }
}

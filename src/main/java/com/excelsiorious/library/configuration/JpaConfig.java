package com.excelsiorious.library.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("com.excelsiorious.library")
@PropertySource("classpath:database/database.properties")
public class JpaConfig {
    private static final String PROPERTY_SQL_SCHEMA = "sql.schema";
    private static final String PROPERTY_SQL_INIT_DATA = "sql.init.data";
    private static final String DRIVER_NAME = "org.postgresql.Driver";
    private static final String PROPERTY_POSTGRES_URL = "postgres.url";
    private static final String PROPERTY_POSTGRES_USERNAME = "postgres.username";
    private static final String PROPERTY_POSTGRES_PASSWORD = "postgres.password";
    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final String PACKAGES_TO_SCAN = "com.excelsiorious.library";

    @Autowired
    Environment environment;

    @Bean(name = "dataSource")
    DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(environment.getProperty(PROPERTY_POSTGRES_URL));
        dataSource.setUsername(environment.getProperty(PROPERTY_POSTGRES_USERNAME));
        dataSource.setPassword(environment.getProperty(PROPERTY_POSTGRES_PASSWORD));
        return dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScripts(new ClassPathResource(environment.getProperty(PROPERTY_SQL_SCHEMA)),
                new ClassPathResource(environment.getProperty(PROPERTY_SQL_INIT_DATA)));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource());
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        return dataSourceInitializer;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setShowSql(Boolean.parseBoolean(environment.getProperty(PROPERTY_HIBERNATE_SHOW_SQL)));

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO, environment.getProperty(PROPERTY_HIBERNATE_HBM2DDL_AUTO));
        hibernateProperties.setProperty(PROPERTY_HIBERNATE_DIALECT, environment.getProperty(PROPERTY_HIBERNATE_DIALECT));
        hibernateProperties.setProperty(PROPERTY_HIBERNATE_SHOW_SQL, environment.getProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        hibernateProperties.setProperty(PROPERTY_HIBERNATE_FORMAT_SQL, environment.getProperty(PROPERTY_HIBERNATE_FORMAT_SQL));
        hibernateProperties.setProperty(PROPERTY_HIBERNATE_USE_SQL_COMMENTS, environment.getProperty(PROPERTY_HIBERNATE_USE_SQL_COMMENTS));
        return hibernateProperties;
    }
}

package com.gmail.arthurstrokov.dao.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Getter
@Component
public class DatabaseProperties {

    private final Environment environment;

    private String databaseUrl;
    private String databaseUsername;
    private String databasePassword;

    private String poolDataSourceClass;
    private Integer poolMaxSize;
    private String poolCachePreparedStatements;
    private Integer poolCachePreparedStatementsSize;
    private Integer poolCachePreparedStatementsSQLLimit;
    private String poolUseServerPreparedStatements;

    private String dialect;
    private String showSQL;
    private String formatSQL;
    private String useSQLComments;
    private String hbm2ddlAuto;
    private String cacheUseSecondLevelCache;
    private String cacheRegionFactory;
    private String generateStatistics;
    private String autoCloseSession;
    private String autocommit;


    @Autowired
    public DatabaseProperties(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void initialize() {
        this.databaseUrl = environment.getProperty("database.url");
        this.databaseUsername = environment.getProperty("database.username");
        this.databasePassword = environment.getProperty("database.password");

        this.poolDataSourceClass = environment.getProperty("pool.data.source.class");
        this.poolMaxSize = Integer.valueOf(Objects.requireNonNull(environment.getProperty("pool.max.size")));
        this.poolCachePreparedStatements = environment.getProperty("pool.cache.prepared.statements");
        this.poolCachePreparedStatementsSize = Integer.valueOf(Objects.requireNonNull(environment.getProperty("pool.cache.prepared.statements.size")));
        this.poolCachePreparedStatementsSQLLimit = Integer.valueOf(Objects.requireNonNull(environment.getProperty("pool.cache.prepared.statements.sql.limit")));
        this.poolUseServerPreparedStatements = environment.getProperty("pool.use.server.prepared.statements");

        this.dialect = environment.getProperty("hibernate.dialect");
        this.showSQL = environment.getProperty("hibernate.show_sql");
        this.formatSQL = environment.getProperty("hibernate.format_sql");
        this.useSQLComments = environment.getProperty("hibernate.use_sql_comments");
        this.hbm2ddlAuto = environment.getProperty("hibernate.hbm2ddl.auto");
        this.cacheUseSecondLevelCache = environment.getProperty("hibernate.cache.use_second_level_cache");
        this.cacheRegionFactory = environment.getProperty("hibernate.cache.region.factory_class");
        this.generateStatistics = environment.getProperty("hibernate.generate_statistics");
        this.autoCloseSession = environment.getProperty("hibernate.transaction.auto_close_session");
        this.autocommit = environment.getProperty("hibernate.connection.autocommit");
    }
}
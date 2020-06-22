package com.sai.easwer.configuration;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for flyway migration pugin.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-10 18:02:57
 * @modify date 2020-03-10 18:03:27
 */
@Configuration
public class FlywayConfiguration {

    private static final String BASE_VERSION = "0";

    /**
     * Set flyway configuration for Database migration.
     * 
     * @param dataSource {@link DataSource}
     */
    @Autowired
    public FlywayConfiguration(final DataSource dataSource) {
        Flyway.configure().baselineOnMigrate(true).baselineVersion(MigrationVersion.fromVersion(BASE_VERSION))
                .dataSource(dataSource).load().migrate();
    }
}

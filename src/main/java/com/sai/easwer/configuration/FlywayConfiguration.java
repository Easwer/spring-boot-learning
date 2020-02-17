package com.sai.easwer.configuration;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

    @Autowired
    public FlywayConfiguration(final DataSource dataSource) {
        Flyway.configure().baselineOnMigrate(true).baselineVersion(MigrationVersion.fromVersion("0"))
                .dataSource(dataSource).load().migrate();
    }
}
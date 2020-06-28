package com.sai.easwer;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

// import io.swagger.v3.oas.annotations.info.Info;
// import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 * Spring boot application.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-06-23 15:33:35
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot Application", version = "2.0", contact = @Contact(name = "Easwer AP", email = "easwerms@gmail.com")))
public class ApplicationLauncher implements CommandLineRunner {

	private static final String BASE_VERSION = "0";

	@Autowired
	private DataSource dataSource;

	/**
	 * Spring boot application.
	 * 
	 * @param args {@link String}
	 */
	public static void main(final String[] args) {
		SpringApplication.run(ApplicationLauncher.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Flyway.configure().baselineOnMigrate(true).baselineVersion(MigrationVersion.fromVersion(BASE_VERSION))
				.dataSource(dataSource).load().migrate();
	}

}

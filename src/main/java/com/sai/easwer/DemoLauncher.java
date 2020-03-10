package com.sai.easwer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring boot application.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:09:11
 */
@SpringBootApplication
public class DemoLauncher {

	/**
	 * Spring boot application.
	 * 
	 * @param args {@link String}
	 */
	public static void main(final String[] args) {
		SpringApplication.run(DemoLauncher.class, args);
	}

	private DemoLauncher() {

	}

}

package com.wpetit.projecthome.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Application class.
 *
 * @author nqvn6733
 *
 */
@SpringBootApplication
public class Application {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args).registerShutdownHook();
	}

}
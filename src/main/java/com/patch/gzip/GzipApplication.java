package com.patch.gzip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GzipApplication {

	public static void main(String[] args) {

		GZIPTest.test();
		SpringApplication.run(GzipApplication.class, args);
	}

}

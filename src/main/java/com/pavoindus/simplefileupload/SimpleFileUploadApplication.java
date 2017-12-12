package com.pavoindus.simplefileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created 12/11/2017 21:15
 *
 * @author Deepankar Sharma
 */
@SpringBootApplication
public class SimpleFileUploadApplication extends SpringBootServletInitializer {

  @Override protected SpringApplicationBuilder configure(
      SpringApplicationBuilder builder) {
    return builder.sources(SimpleFileUploadApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(SimpleFileUploadApplication.class, args);
  }
}

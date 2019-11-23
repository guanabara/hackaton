package com.mindera.hackaton.api.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.mindera.hackaton.api.data",
        "com.mindera.hackaton.api.data",
        "com.mindera.hackaton.devices",
        "org.openapitools.configuration"
})
public class Application implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new IllegalStateException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Application.class).run(args);
    }

}

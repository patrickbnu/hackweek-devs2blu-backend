package com.api.hackweek;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoHackweekDevs2bluBackendApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoHackweekDevs2bluBackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.setProperty("GOOGLE_API_KEY", "AIzaSyA4URHJ9xDCchFZel63nbuXMLAG_EvnIpA");
    }
}

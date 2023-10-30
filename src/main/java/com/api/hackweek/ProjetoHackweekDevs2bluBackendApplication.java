package com.api.hackweek;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Gasto Certo",
                version = "1.0",
                description = "This is an API developed for the Hackathon in the +devs2blu program"
        ),
        servers = {
                @Server(
                        description = "Local server",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Production Server",
                        url = "https://gastocerto.com"
                )
        }
)
public class ProjetoHackweekDevs2bluBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoHackweekDevs2bluBackendApplication.class, args);
    }
}

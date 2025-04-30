package br.com.fiap._2tdspwapisecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // habilita o suporte a cache do Spring
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

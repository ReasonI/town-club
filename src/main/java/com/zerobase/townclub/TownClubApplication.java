package com.zerobase.townclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TownClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(TownClubApplication.class, args);
    }

}

package com.application.ene.orgmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
public class OrgManagementApplication {

    static void main(String[] args) {
        SpringApplication.run(OrgManagementApplication.class, args);
    }

}

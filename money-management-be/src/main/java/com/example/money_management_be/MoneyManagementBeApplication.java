package com.example.money_management_be;

import com.example.money_management_be.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class MoneyManagementBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyManagementBeApplication.class, args);
    }

    @Component
    public class DatabaseInit implements CommandLineRunner {

        private final UserService userService;

        public DatabaseInit(UserService userService) {
            this.userService = userService;
        }

        @Override
        public void run(String... args) throws Exception {
//            userService.initUserRoles();
        }
    }
}

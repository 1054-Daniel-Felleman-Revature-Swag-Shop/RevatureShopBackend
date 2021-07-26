package com.revature.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableHystrix
@EnableHystrixDashboard
@EnableTurbine
@EnableEurekaClient
@SpringBootApplication
@ComponentScan("com.revature")
@EnableJpaRepositories("com.revature.shop.accounts.repositories")
@EntityScan("com.revature.shop.accounts.models")
public class AccountApplication {
	public static void main(String[] args) {
		System.out.println("ACCOUNT APPLICATION RUNNING!!!!");
		SpringApplication.run(AccountApplication.class, args);
	}
}

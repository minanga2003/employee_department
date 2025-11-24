package com.example.employee_department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.employee_department", "settings_module"})
@EnableJpaRepositories(basePackages = "settings_module.repository")
@EntityScan(basePackages = "settings_module.entity")
public class EmployeeDepartmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeDepartmentApplication.class, args);
	}
}
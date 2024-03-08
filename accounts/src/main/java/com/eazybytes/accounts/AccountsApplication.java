package com.eazybytes.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.eazybytes.accounts.dto.AccountsContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
/*
 * Annotation of SpringBootApplication did the following annotations:
 * 
 * @ComponentScans({ @ComponentScan("com.eazybytes.accounts.controller")})
 * 
 * @EnableJpaRepositories("com.eazybytes.accounts.repository")
 * 
 * @EntityScan("com.eazybytes.accounts.model")
 * 
 * -> used for tracking and logging events related to persistent entities, or simply
 * entity versioning
 */
@EnableJpaAuditing(auditorAwareRef = "auditawareImpl") /* explicit for selecting one from ApplicationContext based on auditawareImpl*/
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
	 info = @Info(
		title = "Accounts Microservice REST API Documentation",
		description = "EazyBank Accounts Microservice REST API Documentation",
		version = "v1",
		contact = @Contact(name="Quyet Tran", email = "quyettranvu@gmail.com", url = "https://tranvuquyet-portfolio.netlify.app/"),
		license = @License(name = "Apache 2.0", url = "https://tranvuquyet-portfolio.netlify.app/")
	 ),
	 externalDocs = @ExternalDocumentation(
		description = "EazyBank Accounts Microservice REST API Documentation",
		url = "https://www.eazybytes.com/swagger-ui.html"
	 )
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}

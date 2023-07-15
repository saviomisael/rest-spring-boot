package io.github.saviomisael.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@Profile({ "!prod" })
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().info(
				new Info().title("Swagger docs example").description("Swagger docs description.").version("1.0.0"));
	}
}

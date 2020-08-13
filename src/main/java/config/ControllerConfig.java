package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.WebController;

@Configuration
public class ControllerConfig {

	@Bean
	public WebController webController() {
		return new WebController();
	}
}

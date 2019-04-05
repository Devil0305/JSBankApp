

package com.pukanghealth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({@PropertySource(value="file:${EXTERNAL_DIR}/JSAPP/mysql.properties"),
		@PropertySource(value="file:${EXTERNAL_DIR}/JSAPP/url.properties")})
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}

package net.matondo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;

import static net.matondo.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class UdemySpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemySpringAngularApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		new File(USER_FOLDER).mkdirs();
		return new BCryptPasswordEncoder();
	}
}

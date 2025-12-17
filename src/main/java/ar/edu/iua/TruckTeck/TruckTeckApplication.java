package ar.edu.iua.TruckTeck;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TruckTeckApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TruckTeckApplication.class, args);
	}

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Override
	public void run(String... args) throws Exception {
		log.info("Perfil Activo: '{}'", activeProfile);
	}
}

package com.unab.crlospinos;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.unab.crlospinos.utils.AppContexto;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication()
@EnableJpaAuditing
public class CrlospinosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrlospinosApplication.class, args);
		System.out.println("API Corriendo...");

		//SecretKey key=Keys.secretKeyFor(SignatureAlgorithm.HS512);
		//String base64key = Encoders.BASE64.encode(key.getEncoded());
		//System.out.println(base64key);
	}

	@Bean
	public ModelMapper modelmapper(){

		ModelMapper modelmapper=new ModelMapper();

		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return modelmapper;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AppContexto appContexto(){
		return new AppContexto();
	}
}

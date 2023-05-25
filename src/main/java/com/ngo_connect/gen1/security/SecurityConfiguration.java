package com.ngo_connect.gen1.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//import com.cognizant.authorizationService.service.AdminDetailsService;
import org.springframework.security.web.SecurityFilterChain;


//@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
//	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

//	@Autowired
//	AdminDetailsService pmsuserDetailsService;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http

				.csrf()

				.disable()

				// Set unauthorized requests exception handler

				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()

				// Set permissions on endpoints
				.authorizeHttpRequests()
				//.requestMatchers("/api/account/authenticate").permitAll()
				//.requestMatchers("/api/account/register").permitAll()
				//.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.requestMatchers("/**").permitAll();
//				.requestMatchers(
//						"/configuration/ui",
//						"/swagger-resources/**",
//						"/configuration/security",
//						"/webjars/**").permitAll()
//				.requestMatchers("/api/**").authenticated();
		// @formatter:on
		http.cors();
		return http.build();
	}


}
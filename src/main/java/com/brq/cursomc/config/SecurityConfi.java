package com.brq.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfi extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment environment;
	
	private static final String[] PUBLIC_MATCHER = {
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHER_GET = {
			"/produtos/**",
			"/categorias/**"
	};

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		if(Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			httpSecurity.headers().frameOptions().disable();
		}
		
		httpSecurity.cors().and().csrf().disable();
		httpSecurity.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHER_GET).permitAll()
			.antMatchers(PUBLIC_MATCHER).permitAll()
			.anyRequest().authenticated();
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

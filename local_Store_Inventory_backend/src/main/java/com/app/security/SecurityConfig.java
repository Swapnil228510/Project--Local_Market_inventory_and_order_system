package com.app.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService UserDetailsService;
	
	@Autowired
	private JWTRequestFilter jwtFilter;
	
	@Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

	@Bean
	public SecurityFilterChain authorizeRequest(HttpSecurity http) throws Exception {
		
		http
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .exceptionHandling(exception -> 
            exception.authenticationEntryPoint((request, resp, exc) ->
                resp.sendError(HttpStatus.UNAUTHORIZED.value(), "Not yet Authenticated")
            )
        )
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
            		"/auth/signIn",
            		"/swagger*/**",
            		"/v*/api-docs/**",
            		"/users/signUp",
            		"/products",
            		"/uploads/**"
            		).permitAll()
            .anyRequest().authenticated()
            
            // BELOW ARE NOT REQUIRED DUE TO ROLE BASE AUTHENTICATION IS WORKING
//            .requestMatchers("/users/**").authenticated()
//            hasAnyRole("CUSTOMER", "STAFF", "ADMIN")
//            .requestMatchers("/products/addProducts").hasRole("STAFF")
//            .requestMatchers("/users/removeUser/{id}").hasRole("ADMIN")
//            .anyRequest().authenticated()) 
        )
        .exceptionHandling(ex -> ex
                .accessDeniedHandler(accessDeniedHandler)
        )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            
    ;
		
		
		return http.build();
	}
	
	  @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	        provider.setUserDetailsService(UserDetailsService);


	        return provider;
	    }

	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    
	    config.setAllowedOrigins(List.of("http://localhost:5173")); // Allow only your frontend origin
	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")); 
	    config.setAllowedHeaders(List.of("*")); //  Allow all headers (e.g., Authorization, Content-Type)
	    config.setAllowCredentials(true); //  Needed as we're sending JWT tokens (e.g., via cookies or headers)
	    config.addExposedHeader("Content-Type");
	    config.addExposedHeader("Content-Disposition");


	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config); //  Apply to all endpoints
	    return source;
	}

	
}

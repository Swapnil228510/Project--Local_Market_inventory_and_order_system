package com.app.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {
	
	@Value("${SECRET_KEY}")
	private String jwtSecret;
	
	@Value("${EXP_TIMEOUT}")
	private int jwtExpirationMs;
	
	private Key key;
	
	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}
	
	
	
	public String generateJwtToken(Authentication authentication) {
		log.info("generate jwt token " + authentication);
		
		CustomUserDetails userPrincipal = (CustomUserDetails)authentication.getPrincipal();
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("authorities", getAuthoritiesInString(userPrincipal.getAuthorities()));
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(userPrincipal.getUsername())
				.issuedAt(new Date())
				.expiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.and()
				.signWith(key)
				.compact();
	}
	
	  // Extract any claim from the token using a resolver
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    
    // Extract all claims (core method used in multiple places)
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
	
	
	  public String getUserNameFromJwtToken(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }
	
	public String getAuthoritiesInString(Collection<? extends GrantedAuthority> authorities) {
	    String authorityString = authorities.stream()
	    		.map(auth ->{
	    			String role = auth.getAuthority();
	    			if(!role.startsWith("ROLE_")) {
	    				role = "ROLE_" + role;
	    			}
	    			return role;
	    		})
	    		.collect(Collectors.joining(","));
	    		
	    		
//	            .map(GrantedAuthority::getAuthority)
//	            .collect(Collectors.joining(","));
	    System.out.println(authorityString);
	    return authorityString;
	}
	
	  public boolean validateToken(String token, UserDetails userDetails) {
	        final String userName = getUserNameFromJwtToken(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	
	   private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
	
	   // Extract token expiration
	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	    
	    // Extract GrantedAuthorities list from claims
	    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
	        String authString = (String) claims.get("authorities");
	        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authString);
	        authorities.forEach(System.out::println);
	        System.out.println("@@@@ Authories in getAuthoritiesFromClaims "+authString);
	        return authorities;
	    }

	    // Helper to get key if needed externally
	    private Key getKey() {
	        return key;
	    }
	
	
	
	
	
	
	

}

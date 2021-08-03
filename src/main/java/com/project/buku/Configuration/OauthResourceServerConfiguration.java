/**
 * 
 */
package com.project.buku.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author Fikri
 *
 */

@Configuration
@EnableResourceServer
public class OauthResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	private static final String RESOURCE_ID = "resource-server-rest-api";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
    private static final String SECURED_PATTERN = "/api/**";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }
    
	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.authorizeRequests()
				.antMatchers("/", "/about", "/signup", "/csrf",  "/webjars/**", "/swagger-resources/**", "/v2/api-docs", "/swagger-ui.html").permitAll()
				.antMatchers(HttpMethod.POST, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
				.antMatchers(HttpMethod.PUT, SECURED_PATTERN).access(SECURED_WRITE_SCOPE)
                .anyRequest().access(SECURED_READ_SCOPE);
	}

}
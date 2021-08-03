/**
 * 
 */
package com.project.buku.Configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Fikri
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.buku.Resource"))
                .paths(PathSelectors.any())                          
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));   
    }
      
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot - Jersey with Oauth 2.3.5")
                .description("Implementation of Spring boot Oauth")
                .version("1.0")
                .contact(new Contact("Fikri", "", "brullyz@gmail.com"))
                .build();
    }
    
    @Bean
    public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder().scopeSeparator(",")
	        .additionalQueryStringParams(null)
	        .useBasicAuthenticationWithAccessCodeGrant(false).build();
    }

    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html").addResourceLocations(
	        "classpath:/META-INF/resources/");
	    registry.addResourceHandler("/webjars/**").addResourceLocations(
	        "classpath:/META-INF/resources/webjars/");
    }

    private ApiKey apiKey() {
    	return new ApiKey("apiKey", "Authorization", "header");
    }

    private SecurityContext securityContext() {
    	return SecurityContext.builder().securityReferences(defaultAuth())
    		.forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
	    AuthorizationScope authorizationScope = new AuthorizationScope(
	        "global", "accessEverything");
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	    authorizationScopes[0] = authorizationScope;
	    return Arrays.asList(new SecurityReference("apiKey",
	        authorizationScopes));
    }
}

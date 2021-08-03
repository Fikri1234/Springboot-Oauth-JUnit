/**
 * 
 */
package com.project.buku.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.springframework.stereotype.Component;

import com.project.buku.Resource.MUserResource;

/**
 * @author Fikri
 *
 */

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig  {
	
	
//	@PostConstruct
//    public void init() {
////		PackageNamesScanner resourceFinder = new PackageNamesScanner(new String[]{"com.project.buku.Resource"}, true);
////        registerFinder(resourceFinder);
//		packages("com.project.buku");
//		register(RequestContextFilter.class);
////		registerClasses(MUserResource.class);
//    }
	
	public JerseyConfig() {
//		packages("com.project.buku.Resource");
//		register(RequestContextFilter.class);
		
		register(MUserResource.class);
    }

}

/**
 * 
 */
package com.project.buku;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * @author Fikri
 *
 */

@SpringBootTest
public class BukuResourceTests {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String CLIENT_ID = IConstant.CLIENT_ID;
    
    final static String USERNAME = IConstant.USERNAME;
    
    @Autowired
    private Oauth2Helper oauth2Helper;
    
    @Autowired
    private AuthorizationServerTokenServices tokenservice;
    
	@Test
	public String getAccessTokenOauth() throws Exception {
		OAuth2Authentication auth = oauth2Helper.oAuth2Authentication(CLIENT_ID, USERNAME);
		OAuth2AccessToken token = tokenservice.createAccessToken(auth);
		final String accessToken = token.getValue();
		log.info("getAccessTokenOauth={}", accessToken);
		return accessToken;
	}

}

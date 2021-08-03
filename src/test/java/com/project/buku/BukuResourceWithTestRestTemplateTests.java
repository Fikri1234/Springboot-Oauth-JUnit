/**
 * 
 */
package com.project.buku;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Fikri
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class BukuResourceWithTestRestTemplateTests {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private TestRestTemplate restTemplate;
     
    @LocalServerPort
    int randomServerPort;
    
    @Test
    public void testLogin() throws Exception 
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/oauth/token";
        
        String credentials = IConstant.CLIENT_ID + ":" + IConstant.CLIENT_PASS;
		String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
         
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + encodedCredentials);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		String access_token_url = baseUrl;
		access_token_url += "?username=" + IConstant.USERNAME;
		access_token_url += "&password=" + IConstant.PASSWORD;
		access_token_url += "&grant_type=password";
         
        ResponseEntity<String> response = this.restTemplate.postForEntity(access_token_url, request, String.class);
        
        log.info("Access Token Response --------- {} with port: {}", response.getBody(), randomServerPort);
        
		// Get the Access Token From the recieved JSON response
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response.getBody());
		String token = node.path("access_token").asText();
		
		log.info("Get Token Response ---------" + token);
         
        //Verify request succeed
		assertThat(response.getStatusCodeValue(), equalTo(HttpStatus.OK.value()));
    }

}

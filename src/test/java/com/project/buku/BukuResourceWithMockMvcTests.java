/**
 * 
 */
package com.project.buku;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Fikri
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
public class BukuResourceWithMockMvcTests {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	private static final String CLIENT_ID = IConstant.CLIENT_ID;
    
    final static String USERNAME = IConstant.USERNAME;
	
    @Autowired
	private Oauth2Helper oauth2Helper;
	
	@Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
    	mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(filterChainProxy).build();
    }
    
    @Test
	public void UnauthorizedMockMvcTest() throws Exception {
		ResultActions actions = mockMvc.perform(get("/buku/"));
		int status = actions.andReturn().getResponse().getStatus();
		assertThat(status, equalTo(HttpStatus.UNAUTHORIZED.value()));
	}
    
    @Test
    public void accessTokenOkMockMvcTest() throws Exception {
        RequestPostProcessor rpp = oauth2Helper.bearerToken(CLIENT_ID, USERNAME);
        mockMvc.perform(get("/buku/").with(rpp)).andExpect(status().isOk());
    }

}

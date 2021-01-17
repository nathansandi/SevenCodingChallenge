package com.app.sevensenders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import com.app.sevensenders.constants.AppConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class SevenCodingChallengeApplicationTests {
	
    @Autowired
    private WebApplicationContext context;
    @Autowired
	MockMvc buildMvCMock;

    AppConstants appC;
	    
	private static final String SERVICE_ENDPOINT = "http://localhost:8080/";
	
	//Test and valid request
	//T1 - Test a valid request 
	@Test
	void test_Valid_Request() throws Exception {		
		AppConstants.XCDK_LINK="https://xkcd.com/info.0.json";
		AppConstants.POORLY_RSS="http://feeds.feedburner.com/PoorlyDrawnLines.rss";
		buildMvCMock = MockMvcBuilders.webAppContextSetup(context).build();
		buildMvCMock.perform(get(SERVICE_ENDPOINT)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk());
			      
	}

	//T2: Invalid Request 
	//Test JSON Exception in case of page is unavaliable 
	@Test
	void test_JSON_Invalid_URL_Request() throws Exception {
		AppConstants.XCDK_LINK="https://xkcd.com/inf.json";
		AppConstants.POORLY_RSS="http://feeds.feedburner.com/PoorlyDrawnLines.rss";
		buildMvCMock = MockMvcBuilders.webAppContextSetup(context).build();
		buildMvCMock.perform(get(SERVICE_ENDPOINT)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isBadRequest())
			      .andExpect(jsonPath("$.message").value("https://xkcd.com/inf.json"));;
 
	}
	//T3 Ivalid RSS Request 
	//T3 Test invalid link for RSS feed in order to simulate wrong request
	@Test
	void test_RSS_Invalid_URLRequest() throws Exception {
		AppConstants.XCDK_LINK="https://xkcd.com/info.0.json";
		AppConstants.POORLY_RSS="http://feeds.feedburner.com/PoorlyDrwnLines.rs";
		buildMvCMock = MockMvcBuilders.webAppContextSetup(context).build();
		buildMvCMock.perform(get(SERVICE_ENDPOINT)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isBadRequest())
			      .andExpect(jsonPath("$.message").value("http://feeds.feedburner.com/PoorlyDrwnLines.rs"));;
	}
	//T4 - Test if its returning 20 elements
	//Verify if the size of array of elements that is returned is 20 as expected
	@Test
	void test_Returning_Size() throws Exception {		
		AppConstants.XCDK_LINK="https://xkcd.com/info.0.json";
		AppConstants.POORLY_RSS="http://feeds.feedburner.com/PoorlyDrawnLines.rss";
		buildMvCMock = MockMvcBuilders.webAppContextSetup(context).build();
		buildMvCMock.perform(get(SERVICE_ENDPOINT)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
				  .andExpect(jsonPath("$", hasSize(20)));
			      
	}

}

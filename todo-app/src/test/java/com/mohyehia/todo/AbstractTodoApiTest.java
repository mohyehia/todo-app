package com.mohyehia.todo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.mohyehia.todo.entities.ApiUser;
import com.mohyehia.todo.entities.SigninRequest;
import com.mohyehia.todo.services.UserService;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = TodoAppApplication.class)
@AutoConfigureMockMvc
public abstract class AbstractTodoApiTest {
	
	private final String USERNAME_FOR_TEST = "moh@mail.com";
	private final String PASSWORD_FOR_TEST = "0000";
	private final String AUTH_HEADER = "Authorization";
	
	@Autowired
	protected MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@Before
	public void setUp() {
		final ApiUser apiUser = new ApiUser(USERNAME_FOR_TEST, new BCryptPasswordEncoder().encode(PASSWORD_FOR_TEST), "mohammed");
		apiUser.setId("123");
		
		given(userService.loadUserByUsername(apiUser.getUsername())).willReturn(apiUser);
	}
	
	public ResultActions login(String username, String password) throws Exception{
		SigninRequest signinRequest = new SigninRequest(username, password);
		return mockMvc.perform(
				post("/api/auth/login")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(signinRequest))
		);
	}
	
	public MockHttpServletRequestBuilder doGet(String uri) {
		return get(uri).header(AUTH_HEADER, getHeader());
	}
	
	public MockHttpServletRequestBuilder doPost(String uri) {
		return post(uri).header(AUTH_HEADER, getHeader());
	}
	
	private String getHeader() {
		try {
			MvcResult result = login(USERNAME_FOR_TEST, PASSWORD_FOR_TEST).andReturn();
			String token = JsonPath.read(result.getResponse().getContentAsString(), "$.token");
			String header = String.format("Bearer %s", token);
			return header;
		} catch (Exception e) {
			return null;
		}
	}
}

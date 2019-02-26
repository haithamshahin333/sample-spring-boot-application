package puckboard.sample.utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

public class TestUtils {

	public static MockHttpServletResponse callPost(MockMvc mvc, String url, String json) throws Exception {

		MockHttpServletResponse response = mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn().getResponse();

		Assert.assertNotNull(response);
		return response;

	}

	public static MockHttpServletResponse callPut(MockMvc mvc, String url, String json) throws Exception {

		MockHttpServletResponse response = mvc.perform(put(url).contentType(MediaType.APPLICATION_JSON).content(json))
				.andReturn().getResponse();

		Assert.assertNotNull(response);
		return response;

	}

	public static MockHttpServletResponse callGet(MockMvc mvc, String url) throws Exception {

		MockHttpServletResponse response = mvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		Assert.assertNotNull(response);
		return response;

	}

	public static MockHttpServletResponse callDelete(MockMvc mvc, String url) throws Exception {

		MockHttpServletResponse response = mvc.perform(delete(url).contentType(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		Assert.assertNotNull(response);
		return response;

	}

}

package puckboard.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import puckboard.sample.domain.KeyValuePair;
import puckboard.sample.repository.KeyValuePairRepository;
import puckboard.sample.rest.KeyValuePairResource;
import puckboard.sample.service.KeyValuePairService;
import puckboard.sample.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class KeyValuePairResourceTest {

	private static final String BASE_API_PATH = "/api/v1";
	private static final String KEY_VALUE_PAIR_POST_API = BASE_API_PATH + "/keyvaluepair";

	private MockMvc mvc;

	private ObjectMapper mapper = new ObjectMapper();

	private KeyValuePairRepository repository;
	private KeyValuePairService service;
	private KeyValuePairResource resource;

	@Before
	public void setup() {

		repository = new KeyValuePairRepository();
		service = new KeyValuePairService(repository);
		resource = new KeyValuePairResource(service);

		// We would need this line if we would not use MockitoJUnitRunner
		// MockitoAnnotations.initMocks(this);
		// Initializes the JacksonTester
		JacksonTester.initFields(this, mapper);
		// MockMvc standalone approach
		mvc = MockMvcBuilders.standaloneSetup(resource).build();

	}

	@Test
	public void testPostSuccess() throws Exception {

		// given
		KeyValuePair keyValuePair = new KeyValuePair("key1", "value1");

		// when
		String json = mapper.writeValueAsString(keyValuePair);
		MockHttpServletResponse response = TestUtils.callPost(mvc, KEY_VALUE_PAIR_POST_API, json);

		// then
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		String responseJson = response.getContentAsString();
		Assert.assertNotNull(responseJson);
		
		KeyValuePair actual = mapper.readValue(responseJson, KeyValuePair.class);
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getKey());
		Assert.assertEquals("key1", actual.getKey());
		Assert.assertNotNull(actual.getValue());
		Assert.assertEquals("value1", actual.getValue());

	}

	@Test
	public void testPostAlreadyExists() throws Exception {

		// given
		KeyValuePair keyValuePair = new KeyValuePair("key1", "value1");

		// when
		String json = mapper.writeValueAsString(keyValuePair);
		MockHttpServletResponse response = TestUtils.callPost(mvc, KEY_VALUE_PAIR_POST_API, json);

		// Assert success
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		// Try same post again
		response = TestUtils.callPost(mvc, KEY_VALUE_PAIR_POST_API, json);
		Assert.assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());

	}

	@Test
	public void testPutSuccess() throws Exception {

		// given
		KeyValuePair keyValuePair = new KeyValuePair("key1", "value1");

		// when
		String json = mapper.writeValueAsString(keyValuePair);
		MockHttpServletResponse response = TestUtils.callPost(mvc, KEY_VALUE_PAIR_POST_API, json);

		// Assert success
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		// Call PUT to change value
		json = mapper.writeValueAsString(new KeyValuePair("key1", "value32"));
		response = TestUtils.callPut(mvc, KEY_VALUE_PAIR_POST_API, json);

		// then
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

		String responseJson = response.getContentAsString();
		Assert.assertNotNull(responseJson);
		
		KeyValuePair actual = mapper.readValue(responseJson, KeyValuePair.class);
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getKey());
		Assert.assertEquals("key1", actual.getKey());
		Assert.assertNotNull(actual.getValue());
		Assert.assertEquals("value32", actual.getValue());

	}

	@Test
	public void testGetSuccess() throws Exception {

		// given
		KeyValuePair keyValuePair = new KeyValuePair("key1", "value1");

		// when
		String json = mapper.writeValueAsString(keyValuePair);
		MockHttpServletResponse response = TestUtils.callPost(mvc, KEY_VALUE_PAIR_POST_API, json);

		// Assert success
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		// Call GET 
		response = TestUtils.callGet(mvc, KEY_VALUE_PAIR_POST_API + "/" + keyValuePair.getKey());

		// then
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

		String responseJson = response.getContentAsString();
		Assert.assertNotNull(responseJson);
		
		KeyValuePair actual = mapper.readValue(responseJson, KeyValuePair.class);
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getKey());
		Assert.assertEquals("key1", actual.getKey());
		Assert.assertNotNull(actual.getValue());
		Assert.assertEquals("value1", actual.getValue());

	}

	@Test
	public void testGetResourceNotFound() throws Exception {

		// given


		// when
		MockHttpServletResponse response = TestUtils.callGet(mvc, KEY_VALUE_PAIR_POST_API + "/key1");

		// then
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

	}

	@Test
	public void testDeleteSuccess() throws Exception {

		// given
		KeyValuePair keyValuePair = new KeyValuePair("key1", "value1");

		// when
		String json = mapper.writeValueAsString(keyValuePair);
		MockHttpServletResponse response = TestUtils.callPost(mvc, KEY_VALUE_PAIR_POST_API, json);

		// Assert success
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		// Call DELETE 
		response = TestUtils.callDelete(mvc, KEY_VALUE_PAIR_POST_API + "/" + keyValuePair.getKey());

		// then
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());

		String responseJson = response.getContentAsString();
		Assert.assertNotNull(responseJson);
		
		KeyValuePair actual = mapper.readValue(responseJson, KeyValuePair.class);
		Assert.assertNotNull(actual);
		Assert.assertNotNull(actual.getKey());
		Assert.assertEquals("key1", actual.getKey());
		Assert.assertNotNull(actual.getValue());
		Assert.assertEquals("value1", actual.getValue());

	}

	@Test
	public void testDeleteResourceNotFound() throws Exception {

		// given

		// when
		MockHttpServletResponse response = TestUtils.callDelete(mvc, KEY_VALUE_PAIR_POST_API + "/key1");

		// then
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

		String responseJson = response.getContentAsString();
		Assert.assertNotNull(responseJson);

	}


}

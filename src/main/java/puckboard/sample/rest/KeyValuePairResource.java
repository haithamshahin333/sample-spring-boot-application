package puckboard.sample.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import puckboard.sample.domain.KeyValuePair;
import puckboard.sample.service.KeyValuePairService;

@RestController
@RequestMapping("/api/v1")
public class KeyValuePairResource {

	private KeyValuePairService service;

	@Autowired
	public KeyValuePairResource(KeyValuePairService service) {
		this.service = service;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/keyvaluepair", method = RequestMethod.POST)
	public KeyValuePair create(@RequestBody KeyValuePair keyValuePair) {
		return service.create(keyValuePair);
	}

	@RequestMapping(path = "/keyvaluepair", method = RequestMethod.PUT)
	public KeyValuePair update(@RequestBody KeyValuePair keyValuePair) {
		return service.update(keyValuePair);
	}

	@RequestMapping(path = "/keyvaluepair/{key}", method = RequestMethod.GET)
	public KeyValuePair get(@PathVariable String key) {
		return service.get(key);
	}

	@RequestMapping(path = "/keyvaluepair/{key}", method = RequestMethod.DELETE)
	public KeyValuePair delete(@PathVariable String key) {
		return service.delete(key);
	}

}

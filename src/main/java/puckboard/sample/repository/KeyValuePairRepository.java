package puckboard.sample.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import puckboard.sample.domain.KeyValuePair;
import puckboard.sample.exception.ResourceExistsException;
import puckboard.sample.exception.ResourceNotFoundException;

@Component
public class KeyValuePairRepository {

	private Map<String, String> map = new HashMap<String, String>();

	public KeyValuePair create(KeyValuePair keyValuePair) {

		if (map.containsKey(keyValuePair.getKey())) {
			throw new ResourceExistsException("Resource already exists with key '" + keyValuePair.getKey() + "'");
		}

		map.put(keyValuePair.getKey(), keyValuePair.getValue());

		return keyValuePair;

	}

	public KeyValuePair update(KeyValuePair keyValuePair) {

		if (map.containsKey(keyValuePair.getKey())) {
			map.put(keyValuePair.getKey(), keyValuePair.getValue());
		} else {
			create(keyValuePair);
		}

		return keyValuePair;

	}

	public KeyValuePair get(String key) {

		if (!map.containsKey(key)) {
			throw new ResourceNotFoundException("No resource found with key '" + key + "'");
		}

		return new KeyValuePair(key, map.get(key));

	}

	public KeyValuePair delete(String key) {

		if (!map.containsKey(key)) {
			throw new ResourceNotFoundException("No resource found with key '" + key + "'");
		}

		// get value to return before deleting
		KeyValuePair keyValuePair = new KeyValuePair(key, map.get(key));

		// remove the value
		map.remove(key);

		return keyValuePair;

	}

}

package puckboard.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import puckboard.sample.domain.KeyValuePair;
import puckboard.sample.repository.KeyValuePairRepository;

@Component
public class KeyValuePairService {

	private KeyValuePairRepository repository;

	@Autowired
	public KeyValuePairService(KeyValuePairRepository repository) {
		this.repository = repository;
	}

	public KeyValuePair create(KeyValuePair keyValuePair) {
		return repository.create(keyValuePair);
	}

	public KeyValuePair update(KeyValuePair keyValuePair) {
		return repository.update(keyValuePair);
	}

	public KeyValuePair get(String key) {
		return repository.get(key);
	}

	public KeyValuePair delete(String key) {
		return repository.delete(key);
	}

}

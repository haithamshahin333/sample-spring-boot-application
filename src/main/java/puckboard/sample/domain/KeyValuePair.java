package puckboard.sample.domain;

import lombok.Data;

@Data
public class KeyValuePair {

	private String key;
	private String value;

	public KeyValuePair() {
	}

	public KeyValuePair(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

}

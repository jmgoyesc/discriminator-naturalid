package com.personal.hibernate.model;

/**
 * Event entity
 * Created by juan.goyes on 2017-05-02.
 */
public class Event {
	private String id;
	private String code;
	private String name;

	public Event() {
	}

	public Event(int index) {
		this.code = String.valueOf(index);
		this.name = "Event " + index;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

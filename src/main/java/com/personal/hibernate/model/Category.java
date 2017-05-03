package com.personal.hibernate.model;

/**
 * Category entity
 * Created by juan.goyes on 2017-05-02.
 */
public class Category {
	private String id;
	private String code;
	private String name;

	public Category() {
	}

	public Category(int index){
		this.code = String.valueOf(index);
		this.name = "Category "+index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

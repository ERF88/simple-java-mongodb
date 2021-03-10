package com.github.erf88;

import org.bson.Document;

public class Customer {

	private String id;
	private String name;
	private String email;

	public Customer() {
	}

	public Customer(Document document) {
		this.id = document.get("_id").toString();
		this.name = document.getString("name");
		this.email = document.getString("email");
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String age) {
		this.email = age;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
	}

}

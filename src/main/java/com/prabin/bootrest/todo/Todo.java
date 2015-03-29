package com.prabin.bootrest.todo;

import org.springframework.data.annotation.Id;

public class Todo {
	
	static final int MAX_LENGTH_DESCRIPTION = 500;
	static final int MAX_LENGTH_TITLE = 100;
	
	@Id
	private String id;
	
	private String description;
	
	private String title;
	
	public Todo() {}
	
	private Todo(Builder builder) {
		this.description = builder.description;
		this.title = builder.title;
	}
	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}
	
	public void update(String title, String description) {
		checkTitleAndDescription(title, description);
		this.title = title;
		this.description = description;
	}

	//Do validation via jpa TODO:Prabin
	private void checkTitleAndDescription(String title2, String description2) {
		if(null == title2) {
			throw new NullPointerException("title can't be null");
		}
		if(title2.isEmpty()) {
			throw new IllegalArgumentException("title can't be empty");
		}
	}

	static class Builder {
		private String description;
		private String title;
		
		private Builder(){}
		
		Builder description(String description) {
			this.description = description;
			return this;
		}
		
		Builder title(String title) {
			this.title = title;
			return this;
		}
		
		Todo build() {
			Todo build = new Todo(this);
			build.checkTitleAndDescription(build.getTitle(), build.getDescription());
			return build;
		}
	}
}

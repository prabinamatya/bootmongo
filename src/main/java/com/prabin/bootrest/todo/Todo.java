package com.prabin.bootrest.todo;

import org.springframework.data.annotation.Id;

public class Todo {
	
	public static final int MAX_LENGTH_DESCRIPTION = 500;
	public static final int MAX_LENGTH_TITLE = 100;
	
	@Id
	private String id;
	
	private String description;
	
	private String title;
	
	public Todo() {}
	
	private Todo(TodoBuilder builder) {
		this.description = builder.description;
		this.title = builder.title;
	}
	
	public static TodoBuilder getBuilder() {
		return new TodoBuilder();
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

	public static class TodoBuilder {
		private String description;
		private String title;
		
		public TodoBuilder(){}
		
		public TodoBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public TodoBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public Todo build() {
			Todo build = new Todo(this);
			build.checkTitleAndDescription(build.getTitle(), build.getDescription());
			return build;
		}
	}
}

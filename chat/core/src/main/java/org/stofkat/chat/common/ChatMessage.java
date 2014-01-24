package org.stofkat.chat.common;

public class ChatMessage {
	private int id;
	private String authorName;
	private String authorSessionId;
	private String message;
	private long timestamp;
	
	public ChatMessage(int id, String authorName, String authorSessionId, String message, long timestamp) {
		this.id = id;
		this.authorName = authorName;
		this.authorSessionId = authorSessionId;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public int getId() {
		return id;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	
	public String getAuthorSessionId() {
		return authorSessionId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
}

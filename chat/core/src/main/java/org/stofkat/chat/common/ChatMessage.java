package org.stofkat.chat.common;

public class ChatMessage {
	private int id;
	private String authorName;
	private String authorSessionId;
	private String message;
	private long timestamp;
	
	/**
	 *  We need this empty constructor to be able to transmit this object via GWT RPC.
	 */
	public ChatMessage() {}
	
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

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setAuthorSessionId(String authorSessionId) {
		this.authorSessionId = authorSessionId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return authorName + ": " + message;
	}
}

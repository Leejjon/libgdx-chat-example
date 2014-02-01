package org.stofkat.chat.common.actions;

import org.stofkat.chat.common.results.ChatResult;

public class ChatAction implements Action<ChatResult> {
	private static final long serialVersionUID = 1L;
	
	private String author;
	
	private String message;
	
	private int lastReceivedChatMessageId;
	
	/**
	 *  We need this empty constructor to be able to transmit this object via GWT RPC.
	 */
	public ChatAction() {}
	
	public ChatAction(String author, String message, int lastReceivedChatmessageId) {
		this.author = author;
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getLastReceivedChatMessageId() {
		return lastReceivedChatMessageId;
	}

	public void setLastReceivedChatMessageId(int lastReceivedChatMessageId) {
		this.lastReceivedChatMessageId = lastReceivedChatMessageId;
	}
}

package org.stofkat.chat.common.actions;

import org.stofkat.chat.common.results.ChatResult;

public class UpdateAction implements Action<ChatResult> {
	private static final long serialVersionUID = 1L;
	
	private int lastReceivedChatMessageId;
	
	/**
	 *  We need this empty constructor to be able to transmit this object via GWT RPC.
	 */
	public UpdateAction() {}
	
	public UpdateAction(int lastReceivedChatMessageId) {
		this.lastReceivedChatMessageId = lastReceivedChatMessageId;
	}

	public int getLastReceivedChatMessageId() {
		return lastReceivedChatMessageId;
	}

	public void setLastReceivedChatMessageId(int lastReceivedChatMessageId) {
		this.lastReceivedChatMessageId = lastReceivedChatMessageId;
	}
}

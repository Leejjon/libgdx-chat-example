package org.stofkat.chat.common.actions;

import org.stofkat.chat.common.ChatMessage;
import org.stofkat.chat.common.results.ChatResult;

public class ChatAction implements Action<ChatResult> {
	private static final long serialVersionUID = 1L;
	
	private ChatMessage message;
	
	/**
	 *  We need this empty constructor to be able to transmit this object via GWT RPC.
	 */
	public ChatAction() {}
	
	public ChatAction(ChatMessage message, int latestReceivedChatmessage) {
		
	}
	
}

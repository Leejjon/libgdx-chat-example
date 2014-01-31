package org.stofkat.chat.common.results;

import java.util.List;

import org.stofkat.chat.common.ChatMessage;
import org.stofkat.chat.common.ClientInterface;

public class ChatResult implements Result {
	private static final long serialVersionUID = 1L;
	
	private List<ChatMessage> newMessages;
	
	/**
	 *  We need this empty constructor to be able to transmit this object via GWT RPC.
	 */
	public ChatResult() {}
	
	public ChatResult(List<ChatMessage> newMessages) {
		this.newMessages = newMessages;
	}
	
	@Override
	public void processResult(ClientInterface clientInterface) {
		// TODO Auto-generated method stub
		
	}
	
	public List<ChatMessage> getNewMessages() {
		return newMessages;
	}
	
	public void setNewMessages(List<ChatMessage> newMessages) {
		this.newMessages = newMessages;
	}
 }

package org.stofkat.chat.common.results;

import java.util.ArrayList;
import java.util.List;

import org.stofkat.chat.common.ChatMessage;
import org.stofkat.chat.common.ClientInterface;

public class ChatResult implements Result {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ChatMessage> newMessages;
	
	/**
	 *  We need this empty constructor to be able to transmit this object via GWT RPC.
	 */
	public ChatResult() {}
	
	public ChatResult(ArrayList<ChatMessage> newMessages) {
		this.newMessages = newMessages;
	}
	
	@Override
	public void processResult(ClientInterface clientInterface) {
		clientInterface.updateList(newMessages);
	}
	
	public List<ChatMessage> getNewMessages() {
		return newMessages;
	}
	
	public void setNewMessages(ArrayList<ChatMessage> newMessages) {
		this.newMessages = newMessages;
	}
 }

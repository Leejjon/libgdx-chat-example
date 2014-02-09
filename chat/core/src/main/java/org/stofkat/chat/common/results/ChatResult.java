package org.stofkat.chat.common.results;

import java.util.ArrayList;
import java.util.List;

import org.stofkat.chat.common.ChatMessage;
import org.stofkat.chat.common.ClientInterface;

public class ChatResult implements Result {
	private static final long serialVersionUID = 1L;
	
	private boolean resetLastId;
	
	private ArrayList<ChatMessage> newMessages;
	
	/**
	 *  We need this empty constructor to be able to transmit this object via GWT RPC.
	 */
	public ChatResult() {}
	
	public ChatResult(ArrayList<ChatMessage> newMessages, boolean resetLastId) {
		this.newMessages = newMessages;
		this.resetLastId = resetLastId;
	}
	
	@Override
	public void processResult(ClientInterface clientInterface) {
		clientInterface.updateList(newMessages, resetLastId);
	}
	
	public List<ChatMessage> getNewMessages() {
		return newMessages;
	}
	
	public void setNewMessages(ArrayList<ChatMessage> newMessages) {
		this.newMessages = newMessages;
	}

	public boolean isResetLastId() {
		return resetLastId;
	}

	public void setResetLastId(boolean resetLastId) {
		this.resetLastId = resetLastId;
	}
 }

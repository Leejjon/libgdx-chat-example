package org.stofkat.chat.server.actionhandlers;

import javax.servlet.http.HttpSession;

import org.apache.http.protocol.ExecutionContext;
import org.stofkat.chat.common.actions.ChatAction;
import org.stofkat.chat.common.exceptions.DispatchException;
import org.stofkat.chat.common.results.ChatResult;
import org.stofkat.chat.server.ActionHandler;
import org.stofkat.chat.server.ChatMessagesDatabase;

public class ChatActionHandler implements ActionHandler<ChatAction, ChatResult> {

	@Override
	public Class<ChatAction> getActionType() {
		return ChatAction.class;
	}

	@Override
	public ChatResult execute(ChatAction action, ExecutionContext context, HttpSession session)
			throws DispatchException {
		ChatMessagesDatabase db = ChatMessagesDatabase.getInstance();
		
		db.postNewMessage(action.getAuthor(), session.getId(), action.getMessage());
		
		return new ChatResult(db.getLatestMessages(action.getLastReceivedChatMessageId()));
	}

	@Override
	public void rollback(ChatAction action, ChatResult result, ExecutionContext context) throws DispatchException {
		// Eventually you could make something happen if an action fails.
	}

}

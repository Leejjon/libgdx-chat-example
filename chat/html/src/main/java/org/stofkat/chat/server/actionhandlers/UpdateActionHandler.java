package org.stofkat.chat.server.actionhandlers;

import javax.servlet.http.HttpSession;

import org.apache.http.protocol.ExecutionContext;
import org.stofkat.chat.common.actions.UpdateAction;
import org.stofkat.chat.common.exceptions.DispatchException;
import org.stofkat.chat.common.results.ChatResult;
import org.stofkat.chat.server.ActionHandler;
import org.stofkat.chat.server.ChatMessagesDatabase;

public class UpdateActionHandler implements ActionHandler<UpdateAction, ChatResult> {

	@Override
	public Class<UpdateAction> getActionType() {
		return UpdateAction.class;
	}

	@Override
	public ChatResult execute(UpdateAction action, ExecutionContext context, HttpSession session)
			throws DispatchException {
		ChatMessagesDatabase db = ChatMessagesDatabase.getInstance();
		return new ChatResult(db.getLatestMessages(action.getLastReceivedChatMessageId()));
	}

	@Override
	public void rollback(UpdateAction action, ChatResult result, ExecutionContext context) throws DispatchException {
		// Not implemented.
	}

}

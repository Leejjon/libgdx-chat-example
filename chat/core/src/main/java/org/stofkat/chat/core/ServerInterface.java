package org.stofkat.chat.core;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;

public interface ServerInterface {
	<R extends Result> void executeServerAction(Action<R> action);
	
	int getLastChatMessageId();
	
	/**
	 * When posting a chat message, you get new chat updates in the result.
	 * There's also a timer that gets updates every few seconds.
	 * 
	 * To avoid getting stuff twice, you should pause the timer when posting
	 * a chat message.
	 */
	void pauseTimerBecauseWereGonnaUpdate();
}

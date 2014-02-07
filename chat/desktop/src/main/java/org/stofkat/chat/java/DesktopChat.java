package org.stofkat.chat.java;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;
import org.stofkat.chat.core.Chat;
import org.stofkat.chat.http.dispatch.AsyncCallbackHandler;
import org.stofkat.chat.http.dispatch.HttpDispatchServiceAsync;

public class DesktopChat extends Chat {
	private HttpDispatchServiceAsync server = new DesktopDispatchServiceAsync();

	@Override
	public <R extends Result> void executeServerAction(Action<R> action) {
		server.execute(action, new AsyncCallbackHandler<R>(this));
	}

}

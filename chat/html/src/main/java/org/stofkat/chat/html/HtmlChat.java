package org.stofkat.chat.html;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;
import org.stofkat.chat.core.Chat;
import org.stofkat.chat.html.dispatch.GwtAsyncCallbackHandler;
import org.stofkat.chat.html.dispatch.GwtDispatchService;
import org.stofkat.chat.html.dispatch.GwtDispatchServiceAsync;

import com.google.gwt.core.client.GWT;

public class HtmlChat extends Chat {
	private final GwtDispatchServiceAsync chatService = GWT.create(GwtDispatchService.class);
	
	
	@Override
	protected void loadUIStuff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <R extends Result> void executeServerAction(Action<R> action) {
		chatService.execute(action, new GwtAsyncCallbackHandler<R>(this));
	}

}

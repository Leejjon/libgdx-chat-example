package org.stofkat.chat.html.dispatch;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtDispatchServiceAsync {
	<R extends Result> void execute(Action<R> action, AsyncCallback<R> callback);
}

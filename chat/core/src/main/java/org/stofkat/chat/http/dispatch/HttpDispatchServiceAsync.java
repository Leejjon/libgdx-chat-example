package org.stofkat.chat.http.dispatch;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;

public interface HttpDispatchServiceAsync {
	/**
	 * Executes the specified action.
	 * 
	 * @param action
	 *            The action to execute.
	 * @param callback
	 *            The callback to execute once the action completes.
	 * 
	 * @see net.customware.http.dispatch.server.Dispatch
	 */
	<R extends Result> void execute(Action<R> action, AsyncCallback<R> callback);
}

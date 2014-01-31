package org.stofkat.chat.html.dispatch;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.exceptions.DispatchException;
import org.stofkat.chat.common.results.Result;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("Chat-gwt")
public interface GwtDispatchService extends RemoteService {
	<R extends Result> R execute( Action<R> action ) throws DispatchException;
}

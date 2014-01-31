package org.stofkat.chat.html.dispatch;

import org.stofkat.chat.common.ClientInterface;
import org.stofkat.chat.common.results.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * This class basically catches all results from actions.
 * 
 * @author Leejjon
 *
 * @param <R> The return object should always extend from the Result object.
 */
public class GwtAsyncCallbackHandler<R extends Result> implements AsyncCallback<R> {
	private ClientInterface client;
	
	public GwtAsyncCallbackHandler(ClientInterface engine) {
		this.client = engine;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		caught.printStackTrace();
		
	    client.close();
	}

	@Override
	public void onSuccess(R result) {
		result.processResult(client);
	}
}

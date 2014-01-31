package org.stofkat.chat.http.dispatch;

import org.stofkat.chat.common.ClientInterface;
import org.stofkat.chat.common.results.Result;

public class AsyncCallbackHandler<R extends Result> implements AsyncCallback<R> {
	private ClientInterface client;
	
	public AsyncCallbackHandler(ClientInterface client) {
		this.client = client;
	}
	
	@Override
	public void onFailure(Throwable caught) {
		caught.printStackTrace();
		// TODO Log the exception and show an error to the user.
		client.close();
	}

	@Override
	public void onSuccess(R result) {
		result.processResult(client);
	}
}

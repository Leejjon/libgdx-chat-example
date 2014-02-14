package org.stofkat.chat.java;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;
import org.stofkat.chat.http.dispatch.AsyncCallback;
import org.stofkat.chat.http.dispatch.CustomCookieStore;
import org.stofkat.chat.http.dispatch.HttpDispatchServiceAsync;
import org.stofkat.chat.http.dispatch.HttpUtils;

public class DesktopDispatchServiceAsync implements HttpDispatchServiceAsync {
	/**
	 * Replace stofkat.org by the IP of your server (or 127.0.0.1 if you're
	 * running local).
	 */
	private final String dispatchServiceUri = "http://leejjon.net:8080/chat/Chat-http";
	private HttpClient httpClient;
	private CustomCookieStore cookieStore;

	public DesktopDispatchServiceAsync() {
		httpClient = HttpUtils.getHttpClient(new PoolingClientConnectionManager());
		cookieStore = new CustomCookieStore();
	}

	public Object getResult(Action<?> action) {
		return HttpUtils.getResult(dispatchServiceUri, httpClient, cookieStore, action);
	}

	@Override
	public <R extends Result> void execute(final Action<R> action, final AsyncCallback<R> callback) {
		new Thread() {
			@Override
			public void run() {
				final Object result = getResult(action);
				HttpUtils.processResult(result, callback);
			}
		}.start();
	}
}

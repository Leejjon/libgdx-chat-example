package org.stofkat.chat.android;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;
import org.stofkat.chat.http.dispatch.AsyncCallback;
import org.stofkat.chat.http.dispatch.CustomCookieStore;
import org.stofkat.chat.http.dispatch.HttpDispatchServiceAsync;
import org.stofkat.chat.http.dispatch.HttpUtils;

import android.os.Handler;
import android.os.Looper;

public class AndroidDispatchServiceAsync implements HttpDispatchServiceAsync {
	/**
	 * Replace stofkat.org with the IP address of your server.
	 */
	private final String dispatchServiceUri = "http://stofkat.org:8080/" + "Chat-http";
	private HttpClient httpClient;
	private CustomCookieStore cookieStore;
	
	public AndroidDispatchServiceAsync() {
		// Create and initialize HTTP parameters
		HttpParams params = new BasicHttpParams();
		
		ConnManagerParams.setMaxTotalConnections(params, 100);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);

		// Create and initialize scheme registry
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));

		// Create an HttpClient with the ThreadSafeClientConnManager.
		// This connection manager must be used if more than one thread will
		// be using the HttpClient.
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params,
				schemeRegistry);
		
		httpClient = HttpUtils.getHttpClient(cm);
		cookieStore = new CustomCookieStore();
	}
	
	public Object getResult(Action<?> action) {
		return HttpUtils.getResult(dispatchServiceUri, httpClient, cookieStore,
				action);
	}
	
	@Override
	@SuppressWarnings("hiding")
	public <R extends Result> void execute(final Action<R> action,
			final AsyncCallback<R> callback) {
		// allows non-"edt" thread to be re-inserted into the "edt" queue
		final Handler uiThreadCallback = new Handler(Looper.getMainLooper()); 
		// Leejjon: Added Looper.getMainLooper() to the Handler constructor because it gave this error when a second call was initiated from another thread:
		// http://stackoverflow.com/questions/4187960/asynctask-and-looper-prepare-error
		
		new Thread() {
			@Override
			public void run() {
				final Object result = getResult(action);
				// performs rendering in the "edt" thread, after background
				// operation is
				// complete
				final Runnable runInUIThread = new Runnable() {
					@Override
					public void run() {
						HttpUtils.processResult(result, callback);
					}

				};
				uiThreadCallback.post(runInUIThread);
			}
		}.start();
	}
}

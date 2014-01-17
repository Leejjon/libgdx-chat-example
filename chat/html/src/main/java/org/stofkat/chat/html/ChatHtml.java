package org.stofkat.chat.html;

import org.stofkat.chat.core.Chat;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class ChatHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new Chat();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}

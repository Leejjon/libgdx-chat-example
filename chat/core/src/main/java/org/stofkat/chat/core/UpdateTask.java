package org.stofkat.chat.core;

import org.stofkat.chat.common.actions.UpdateAction;

import com.badlogic.gdx.utils.Timer.Task;

public class UpdateTask extends Task {
	private ServerInterface server;
	
	public UpdateTask(ServerInterface server) {
		this.server = server;
	}
	
	@Override
	public void run() {
		server.executeServerAction(new UpdateAction(server.getLastChatMessageId()));
	}

}

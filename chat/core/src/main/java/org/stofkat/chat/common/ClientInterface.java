package org.stofkat.chat.common;

import java.util.ArrayList;

public interface ClientInterface {
	void updateList(ArrayList<ChatMessage> newMessages, boolean resetLastId);
	
	void close();
}

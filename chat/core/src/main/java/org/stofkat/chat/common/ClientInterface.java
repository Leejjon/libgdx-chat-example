package org.stofkat.chat.common;

import java.util.ArrayList;

public interface ClientInterface {
	void updateList(ArrayList<ChatMessage> newMessages);
	
	void close();
}

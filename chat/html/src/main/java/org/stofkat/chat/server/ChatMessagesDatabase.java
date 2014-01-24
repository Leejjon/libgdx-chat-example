package org.stofkat.chat.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.stofkat.chat.common.ChatMessage;

/**
 * This isn't an actual database. You probably should use a database in a real
 * server application, but since the purpose of this project is to be an example
 * that shows how you can make a client/server architecture with libgdx. I
 * didn't include a real database as it would be more complicated to set up.
 * 
 * This "Database" is a singleton. Which means that there will be only one
 * instance of this class on the entire server application. I'm following the
 * Eager Initialization example from
 * http://en.wikipedia.org/wiki/Singleton_pattern.
 * 
 * Also, I've been reading the following article:
 * http://stackoverflow.com/questions/17578299/java-concurrent-array-list-access
 * 
 * To try and get this thread safe, but I'm no expert in this area. So even
 * though my implementation is my best effort at getting it thread safe, it
 * might not be flawless. I probably should create a lot of unit tests to verify
 * if this implementation is safe to use, but you know how it goes.
 * 
 * @author Leejjon
 */
public class ChatMessagesDatabase {
	public static final ChatMessagesDatabase instance = new ChatMessagesDatabase();

	private final int capacity = 200;

	private AtomicInteger nextAvailableId;
	
	private Map<Integer, ChatMessage> chatMessages;

	/**
	 * Private constructor so that it cannot be used in other classes.
	 */
	private ChatMessagesDatabase() {
		chatMessages = Collections.synchronizedMap(new HashMap<Integer,ChatMessage>(capacity));
		nextAvailableId = new AtomicInteger(0);
	}
	
	/**
	 * @param authorName A string that will display the name of the author of the message.
	 * @param message A string containing the message. 
	 */
	public void postNewMessage(String authorName, String authorSessionId, String message) {
		synchronized (chatMessages) {
			int newId = nextAvailableId.get();
			ChatMessage chatMessage = new ChatMessage(newId, authorName, authorSessionId, message, System.currentTimeMillis());
			
			// If the list is already full.
			if (newId >= capacity) {
				// We remove the oldest chat message.
				Integer oldestId = getLowestId(chatMessages.keySet());
				chatMessages.remove(oldestId);
				
				// And add the new chat message.
				chatMessages.put(new Integer(newId), chatMessage);
			} else {
				chatMessages.put(newId, chatMessage);
			}
			
			// Only increment after the new chatMessage has been added.
			long nextId = nextAvailableId.incrementAndGet();
			
			if (nextId == Long.MAX_VALUE) {
				// In order to prevent overflowing the nextAvailableId, reset the chat.
				chatMessages.clear();
				nextAvailableId = new AtomicInteger(0);
			}
		}
	}
	
	public List<ChatMessage> getLatestMessages(int lastValueTheClientHas) {
		List<ChatMessage> newMessagesForClient = new ArrayList<ChatMessage>();
		
		synchronized (chatMessages) {
			for (int i = lastValueTheClientHas; i < nextAvailableId.get(); i++) {
				newMessagesForClient.add(chatMessages.get(new Integer(i)));
			}
		}
		
		return newMessagesForClient;
	}
	
	/**
	 * Use this method instead of the constructor.
	 * 
	 * @return The ChatMessagesDatabase instance shared by the entire server.
	 */
	public static ChatMessagesDatabase getInstance() {
        return instance;
    }
	
	private static Integer getLowestId(Set<Integer> chatMessages) {
		Integer lowestId = new Integer(Integer.MAX_VALUE);
		
		for (Integer id : chatMessages) {
			if (id.longValue() < lowestId) {
				lowestId = id;
			}
		}
		
		return lowestId;
	}
}

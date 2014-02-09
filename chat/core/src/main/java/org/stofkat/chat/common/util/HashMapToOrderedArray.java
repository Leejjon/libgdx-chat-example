package org.stofkat.chat.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.stofkat.chat.common.ChatMessage;

/**
 * Quickly created this util class to fix the sorting. (the client was showing
 * messages in different orders.) You should write some unit tests to verify if
 * it works 100% if you're seriously going to use it.
 * 
 * @author Leejjon
 */
public class HashMapToOrderedArray {

	public static Object[] getOrderedObjectArray(Map<Integer, ChatMessage> chatMessages) {
		HashMap<Integer,ChatMessage> chatMessagesToCopy = new HashMap<Integer,ChatMessage>();
		chatMessagesToCopy.putAll(chatMessages);
		Object[] orderedArray = new Object[chatMessagesToCopy.size()];

		for (int i = 0; i < chatMessages.size(); i++) {
			Integer lowest = getLowestKey(chatMessagesToCopy.values());
			orderedArray[i] = chatMessagesToCopy.get(lowest);
			chatMessagesToCopy.remove(lowest);
		}

		return orderedArray;
	}

	public static Integer getLowestKey(Collection<ChatMessage> chatMessages) {
		Long lowestTimestamp = new Long(Long.MAX_VALUE);
		Integer lowestKey = Integer.MAX_VALUE;
		for (ChatMessage message : chatMessages) {
			if (message.getTimestamp() < lowestTimestamp) {
				lowestTimestamp = message.getTimestamp();
				lowestKey = new Integer(message.getId());
			}
		}

		return lowestKey;
	}
}

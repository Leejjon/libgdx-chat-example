package org.stofkat.chat.common.util;

import java.util.Set;

public class LowestIdFinder {
	public static Integer getLowestId(Set<Integer> chatMessages) {
		Integer lowestId = new Integer(Integer.MAX_VALUE);
		
		for (Integer id : chatMessages) {
			if (id.longValue() < lowestId) {
				lowestId = id;
			}
		}
		
		return lowestId;
	}
}

package org.stofkat.chat.common.results;

import java.io.Serializable;

import org.stofkat.chat.common.ClientInterface;

public interface Result extends Serializable {
	void processResult(ClientInterface clientInterface);
}

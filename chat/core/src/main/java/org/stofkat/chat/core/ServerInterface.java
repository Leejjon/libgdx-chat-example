package org.stofkat.chat.core;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;

public interface ServerInterface {
	<R extends Result> void executeServerAction(Action<R> action);
}

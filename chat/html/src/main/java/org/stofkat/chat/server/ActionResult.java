package org.stofkat.chat.server;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.results.Result;

/**
 * This contains both the original {@link Action} and the {@link Result} of that
 * action.
 * 
 * @author David Peterson
 * 
 * @param <A>
 *            The action type.
 * @param <R>
 *            The result type.
 */
public class ActionResult<A extends Action<R>, R extends Result> {
	private final A action;
	
	private final R result;
	
	public ActionResult(A action, R result) {
		this.action = action;
		this.result = result;
	}
	
	public A getAction() {
        return action;
    }

    public R getResult() {
        return result;
    }
}

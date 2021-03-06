package org.stofkat.chat.server;

import javax.servlet.http.HttpSession;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.exceptions.DispatchException;
import org.stofkat.chat.common.results.Result;

/**
 * Executes actions and returns the results.
 * 
 * @author David Peterson
 */
public interface Dispatch {
	/**
     * Executes the specified action and returns the appropriate result.
     * 
     * @param <T>
     *            The {@link Result} type.
     * @param action
     *            The {@link Action}.
     * @return The action's result.
     * @throws DispatchException
     *             if the action execution failed.
     */
    <A extends Action<R>, R extends Result> R execute(A action, HttpSession session) throws DispatchException;
}

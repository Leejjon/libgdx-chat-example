package org.stofkat.chat.server.gwt.dispatch;

import javax.servlet.http.HttpServletRequest;

import org.stofkat.chat.common.actions.Action;
import org.stofkat.chat.common.exceptions.DispatchException;
import org.stofkat.chat.common.exceptions.ServiceException;
import org.stofkat.chat.common.results.Result;
import org.stofkat.chat.html.dispatch.GwtDispatchService;
import org.stofkat.chat.server.DefaultActionHandlerRegistry;
import org.stofkat.chat.server.Dispatch;
import org.stofkat.chat.server.InstanceActionHandlerRegistry;
import org.stofkat.chat.server.SimpleDispatch;
import org.stofkat.chat.server.actionhandlers.ChatActionHandler;
import org.stofkat.chat.server.actionhandlers.UpdateActionHandler;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GwtDispatchServlet extends RemoteServiceServlet implements GwtDispatchService {
	private static final long serialVersionUID = 1L;
	
	private Dispatch dispatch;
	
	public GwtDispatchServlet() {
		InstanceActionHandlerRegistry registry = new DefaultActionHandlerRegistry();
		registry.addHandler(new ChatActionHandler());
		registry.addHandler(new UpdateActionHandler());
		dispatch = new SimpleDispatch(registry);
	}
	
	public <R extends Result> R execute( Action<R> action ) throws DispatchException {
        try {            
            if ( dispatch == null ) {
                throw new ServiceException("No dispatch found for servlet '" + getServletName() + "' . Please verify your server-side configuration.");
            }
            HttpServletRequest request = this.getThreadLocalRequest(); 
            return dispatch.execute(action, request.getSession());
        } catch ( RuntimeException e ) {
            log( "Exception while executing " + action.getClass().getName() + ": " + e.getMessage(), e );
            throw new ServiceException(e);
        }
    }
}

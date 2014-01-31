package org.stofkat.chat.server;

public class SimpleDispatch extends AbstractDispatch implements Dispatch {
	private final ActionHandlerRegistry registry;

	public SimpleDispatch(ActionHandlerRegistry registry) {
		this.registry = registry;
	}

	@Override
	protected ActionHandlerRegistry getHandlerRegistry() {
		return registry;
	}
}

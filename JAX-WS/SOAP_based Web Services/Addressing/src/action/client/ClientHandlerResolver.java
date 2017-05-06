package action.client;

import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.PortInfo;

import java.util.List;
import java.util.ArrayList;

public class ClientHandlerResolver implements HandlerResolver {
	public List<Handler> getHandlerChain(PortInfo PortInfo) {
		List<Handler> handlerChain = new ArrayList<Handler>();
		handlerChain.add(new ClientSOAPHandler());
		return handlerChain;
	}
}
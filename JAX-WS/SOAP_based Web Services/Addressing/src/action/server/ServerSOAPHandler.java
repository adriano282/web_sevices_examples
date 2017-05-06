package action.server;

import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.Text;
import javax.xml.namespace.QName;
import java.util.Set;
import java.io.ByteArrayOutputStream;

public class ServerSOAPHandler implements SOAPHandler {

	private String getMessageEncoding(SOAPMessage msg) throws SOAPException {
		String encoding = "utf-8";
		if (msg.getProperty(SOAPMessage.CHARACTER_SET_ENCODING) != null) {
			encoding = msg.getProperty(SOAPMessage.CHARACTER_SET_ENCODING).toString();
		}
		return encoding;
	}

	private void dumpSOAPMessage(SOAPMessage msg) {
		if (msg == null) {
			System.out.println("SOAP Message is null :)");
			return;
		}

		System.out.println(" ;)");
		System.out.println(" --------------------------");
		System.out.println("DUMP OF SOAP MESSAGE !!!!!!");
		System.out.println("---------------------------");

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			msg.writeTo(baos);
			System.out.println(baos.toString(getMessageEncoding(msg)));
		}
		catch (Exception e) { e.printStackTrace(); }
	}

	public boolean handleMessage(MessageContext context) {
		System.out.println("ServerSOAPHandler.handleMessage");
		boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outbound) {
			System.out.println("Direction=outbound !");
		}
		else {
			System.out.println("Direction=inbound !");
		}

		try {
			SOAPMessage msg = ((SOAPMessageContext) context).getMessage();
			dumpSOAPMessage(msg);
		} catch (Exception e) { e.printStackTrace(); }

		return true;
	}

	public boolean handleFault(MessageContext context) {
		System.out.println("ServerSOAPHandler.handleFault");
		boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outbound) {
			System.out.println("Direction=outbound");
		}
		else {
			System.out.println("Direction=inbound");
		}

		if (!outbound) {
			try {
				SOAPMessage msg = ((SOAPMessageContext)context).getMessage();
				dumpSOAPMessage(msg);

				if (((SOAPMessageContext)context).getMessage().getSOAPBody().getFault() != null) {
					String detailName = null;
					try {

						detailName = ((SOAPMessageContext)context).getMessage().getSOAPBody().getFault().getDetail().toString();
						System.out.println("detailName" + detailName);
					}
					catch (Exception e) { e.printStackTrace(); }
				}
			}
			catch (Exception e) { e.printStackTrace(); }
		}
		return true;
	}

	public Set getHeaders() {
		return null;
	}

	public void close(MessageContext messageContext) {}
}
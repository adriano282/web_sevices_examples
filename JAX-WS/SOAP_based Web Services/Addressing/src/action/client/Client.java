package action.client;

import javax.xml.ws.WebServiceRef;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.AddressingFeature;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import java.io.ByteArrayOutputStream;
import javax.xml.transform.stream.StreamResult;

import action.client.wsimport.AddNumbersServiceName;
import action.client.wsimport.AddNumbersName;

public class Client {

	@WebServiceRef
	static AddNumbersServiceName service;

	private WebServiceFeature[] enabledRequiredwsf = {new AddressingFeature(true, true)};

	AddNumbersName port;

	private AddNumbersName getPort() {
		System.out.println("Obtain port from service");

		if (service == null) {
			System.out.println("Service is null.");
			System.out.println("Instacing a new Service...");
			service = new AddNumbersServiceName();

		}
		service.setHandlerResolver(new ClientHandlerResolver());

		port = service.getPort(AddNumbersName.class, enabledRequiredwsf);
		return port;
	}

	private W3CEndpointReference getEPR() {
		if (port == null) return null;

		System.out.println("Obtain EPR from port");
		BindingProvider bp = (BindingProvider) port;
		W3CEndpointReference epr = (W3CEndpointReference) bp.getEndpointReference();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		epr.writeTo(new StreamResult(baos));

		System.out.println(baos.toString());
		return epr;
	}

	private void setSOAPActionURI(Object o, String action) {
	    BindingProvider bp = (BindingProvider)o;
        java.util.Map requestContext=bp.getRequestContext();

	    if(requestContext == null) {
			System.err.println("setSOAPActionURI:getRequestContext() returned null");
	    } else {
			requestContext.put(BindingProvider.SOAPACTION_URI_PROPERTY, action);
	    }
	}

	public static void main(String[] args) {
	    try {
	        Client client = new Client();
			client.getPort();
			client.getEPR();
            client.callAddNumbersExplicitInputOutputAction();
            client.callAddNumbersExplicitFaultAction();
        } catch(Exception e) {
            e.printStackTrace();
        }
   }

   public void callAddNumbersExplicitInputOutputAction() {
        try {
			System.out.println("Invoking addNumbersExplicitInputOutputAction operation ...");
			setSOAPActionURI(port, "http://addnumbers.org/targetNamespace/input");
			int result = port.addNumbersExplicitInputOutputAction(10, 10);
			System.out.println("result="+result);
        } catch(Exception e) {
            e.printStackTrace();
        }
   }

   public void callAddNumbersExplicitFaultAction() {
        try {
			System.out.println("Invoking addNumbersExplicitFaultAction operation ...");
			setSOAPActionURI(port, "input");
			int result = port.addNumbersExplicitFaultAction(-10, 10);
			System.out.println("result="+result);
        } catch(Exception e) {
            e.printStackTrace();
        }
   }
   
}
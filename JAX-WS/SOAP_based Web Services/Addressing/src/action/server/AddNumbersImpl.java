package action.server;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.BindingType;
import javax.jws.HandlerChain;

import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.ws.wsaddressing.W3CEndpointReference;

@WebService(
	name="AddNumbersName",
	portName="AddNumbersPortName",
	targetNamespace="http://addnumbers.org/targetNamespace",
	serviceName="AddNumbersServiceName")
@HandlerChain(name ="", file="../../../server-handler.xml")
@BindingType(value=SOAPBinding.SOAP11HTTP_BINDING)
@Addressing(enabled=true, required=true)
public class AddNumbersImpl {

	@Resource
	WebServiceContext wsc;

	public W3CEndpointReference getEPR() {
		return (W3CEndpointReference) wsc.getEndpointReference();
	}

	@Action(
		input="http://addnumbers.org/targetNamespace/input",
		output="http://addnumbers.org/targetNamespace/output")
	public int addNumbersExplicitInputOutputAction(int number1, int number2) throws AddNumberException {
		return addNumbers(number1, number2);
	}

	@Action(
        input="input",
        output="output",
		fault={
                @FaultAction(className=AddNumberException.class, value="http://addnumbers.org/targetNamespace/fault/addnumbers")
            })
    public int addNumbersExplicitFaultAction(int number1, int number2) throws AddNumberException {
        return addNumbers(number1, number2);
    }

	private int addNumbers(int number1, int number2) throws AddNumberException {
		if (number1 < 0 || number2 < 0) {
			throw new AddNumberException("Negative numbers can't be added!", 
				"Numbers: " + number1 + ", " + number2);
		}
		return number1 + number2;
	}

}
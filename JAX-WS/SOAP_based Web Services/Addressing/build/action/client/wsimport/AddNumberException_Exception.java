
package action.client.wsimport;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "AddNumberException", targetNamespace = "http://addnumbers.org/targetNamespace")
public class AddNumberException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private AddNumberException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public AddNumberException_Exception(String message, AddNumberException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public AddNumberException_Exception(String message, AddNumberException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: action.client.wsimport.AddNumberException
     */
    public AddNumberException getFaultInfo() {
        return faultInfo;
    }

}
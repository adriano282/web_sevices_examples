Example Project using WS Addressing with SOAP Headers.

This example uses the Metro Web Services Library.


Web Service generated using wsimport command:

wsimport -p action.client.wsimport -keep http://localhost:8080/addressing?wsdl


The build folder's content was organized using the ant command:

ant -Dwar.name=addressing deploy

The command above create a addressing.war file and deploy the application using the build.xml settings.



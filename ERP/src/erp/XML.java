/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author gabrieltenorio
 */
public class XML {

    public Document CreateXML() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //Create XML
            Document doc = docBuilder.newDocument();

            //<soap:Envelope>
            Element rootElement = doc.createElement("soap:Envelope");
            doc.appendChild(rootElement);
            //<soap:Envelope: Attributes>
            rootElement.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");

            //<soap:Envelope>
            Element Body = doc.createElement("soap:Body");
            rootElement.appendChild(Body);
            
            //Return Doc base XML
            return doc;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        }
    }

    public String Login(Document BaseXML, String user, String pass){

      
            Document FinalDoc = BaseXML;
            Element root = BaseXML.getDocumentElement();
        
        
        try {
            //<soap:Login>
            Element Login = FinalDoc.createElement("Login");
            root.appendChild(Login);
            Login.setAttribute("xmlns", "http://services.zanox.com/erp");

            //<soap:loginname>
            Element loginname = FinalDoc.createElement("loginname");
            loginname.appendChild(FinalDoc.createTextNode(user));
            Login.appendChild(loginname);

            //<soap:password>
            Element password = FinalDoc.createElement("password");
            password.appendChild(FinalDoc.createTextNode(pass));
            Login.appendChild(password);

            //Return
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            StringWriter sw = new StringWriter();
            t.transform(new DOMSource(FinalDoc), new StreamResult(sw));

            String SXML = sw.toString();// Convert to String 
            //System.out.println(SXML);
            
            return SXML;
            
        } catch (TransformerException tfe) {
            System.out.println("ERROR");
            return null;
        }
    }
}

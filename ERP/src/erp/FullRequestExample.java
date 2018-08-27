/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
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
public class FullRequestExample {

    public static void main(String[] args) {

        
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //Create XML
            Document doc = docBuilder.newDocument();

            //<soap:Envelope>
            Element rootElement = doc.createElement("soap:Envelope");
            doc.appendChild(rootElement);
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
            rootElement.setAttribute("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");

            //<soap:Envelope>
            Element Body = doc.createElement("soap:Body");
            rootElement.appendChild(Body);

            //<soap:Login>
            Element Login = doc.createElement("Login");
            Body.appendChild(Login);
            Login.setAttribute("xmlns", "http://services.zanox.com/erp");

            //<soap:loginname>
            Element loginname = doc.createElement("loginname");
            loginname.appendChild(doc.createTextNode("zxbr:gabriel.tenorio"));
            Login.appendChild(loginname);

            //<soap:password>
            Element password = doc.createElement("password");
            password.appendChild(doc.createTextNode("PinkFloyd1"));
            Login.appendChild(password);

            //Convert to String
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            StringWriter sw = new StringWriter();
            t.transform(new DOMSource(doc), new StreamResult(sw));
           
            String SXML = sw.toString();// Convert to String 

            //Request 
            
            //Headers
            String url = "http://services.zanox.com/erp/v2/UserService.asmx"; 
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("SOAPAction", "http://services.zanox.com/erp/Login");
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8'");

           
            
                //Body
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(SXML);
            writer.flush();
            
            //print response
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
               System.out.println(line);                
            }

              //Mix Catch
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (Exception E) {
            System.out.println("Erro= " + E);
        }
    }

}

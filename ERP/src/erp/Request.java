/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author gabrieltenorio
 */
public class Request {

    public String Login(String XML) {
        try {
            //Headers
            String url = "http://services.zanox.com/erp/v2/UserService.asmx";
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true); // Triggers POST.
            connection.setRequestProperty("SOAPAction", "http://services.zanox.com/erp/Login");
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8'");

            //Body
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(XML);
            writer.flush();

            //return XML response
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                return line;
            }
        } catch (Exception E) {
            System.out.println("Erro: " + E);
        }
      return null;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.StringWriter;
import org.w3c.dom.Document;

/**
 *
 * @author gabrieltenorio
 */
public class Session {
    
    // Private instance variables
    private String User;
    private String Password;
    private String ticket;
    
    // Constructors
    public Session(String user, String pass){
        this.User = user;
        this.Password = pass;
    }

    //Getters & Setters
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    //Methods
    public void Autenticate(){
        XML xml = new XML();
        Request request = new Request();
        Document LoginSession = xml.CreateXML();
        String finalXML = xml.Login(LoginSession, this.User, this.Password);
        System.out.println(request.Login(finalXML));
    }

}

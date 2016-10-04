/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diageo.diageomdmweb.mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
//import org.primefaces.model.StreamedContent;

/**
 *
 */
public class EMail {

    private String from;
    private String[] to;
    private String subject;
    private String text;

    public EMail() {
    }

    public EMail(String from, String[] to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    /**
     * Send email
     *
     * @param to
     * @param subject
     * @param text
     */
    public void send(String[] to, String subject, String text) {
        try {
            // TODO code application logic here
            from = "jardila@latino-bi.com";
            this.to = to;
            this.subject = subject;
            this.text = text;
            send("mail.latino-bi.com", "25", "jardila@latino-bi.com", "Latinoardila2016", false);
            //send("10.114.17.110", "25", "GDBSColombia@diageo.com", "", false);
            //mail.send("mail.siesoftware.com", "25", "jhovany.ardila@siesoftware.com", "J3118536968", false);
        } catch (Exception ex) {
            Logger.getLogger(EMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void send(String host, String port, String userName, String password, boolean starttls) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");

        if (starttls) {
            props.put("mail.smtp.starttls.enable", "true");
        }
        Session mailSession = null;
        try {
            mailSession = Session.getInstance(props, new SMTPAuthenticator(userName, password));
        } catch (Exception ex) {
            throw ex;
        }
        Message simpleMessage = new MimeMessage(mailSession);
        InternetAddress fromAddress = null;
        InternetAddress[] toAddress = new InternetAddress[to.length];
        try {
            fromAddress = new InternetAddress(from);
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
        } catch (AddressException ex) {
            throw ex;
        }
        try {
            simpleMessage.setFrom(fromAddress);
            simpleMessage.setRecipients(RecipientType.TO, toAddress);
            simpleMessage.setSubject(subject);

            // contentPart is the content to be sent. It is divided in bodyContent and attachmentContent
            MimeMultipart contentPart = new MimeMultipart("mixed");
            // Message body in txt and html format
            MimeMultipart bodyPart = new MimeMultipart("alternative");
            // Creates html message
            BodyPart bodyHtml = new MimeBodyPart();
            bodyHtml.setContent(text, "text/html");
//            bodyPart.addBodyPart(bodyTxt);
            bodyPart.addBodyPart(bodyHtml);

            // Wrapper for bodyTxt and bodyHtml
            MimeBodyPart bodyContent = new MimeBodyPart();
            bodyContent.setContent(bodyPart);

            // At this point, contentPart contains bodyTxt and bodyHtml wrapped in a multipart/alternative
            contentPart.addBodyPart(bodyContent);
            //
            simpleMessage.setContent(contentPart);
            Transport.send(simpleMessage);
        } catch (MessagingException ex) {
            Logger.getLogger(EMail.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new Exception("Error de envío de correo, por favor verifique "
                    + "los datos digitados o comuníquese con el administrador del sistema");

        }
    }
}

class SMTPAuthenticator extends Authenticator {

    private String userName, password;

    public SMTPAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

}

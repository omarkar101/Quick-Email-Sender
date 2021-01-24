package email;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Send {
	
	
	//This Sends Email without attachment
	public Send(Email from, Email to, String msg, String host, String subject) throws IOException 
	{
		send(from, to, msg, host, subject, "");
	}
	
	//This sends Email with attachment
	public Send(Email from, Email to, String msg, String host, String subject, String attachment) throws IOException 
	{
		send(from, to, msg, host, subject, attachment);
	}
	
	public void send(Email from, Email to, String msg, String host, String subject, String attachment) throws IOException
	{
		String hostName = (host == "Outlook" ? "outlook.office365.com" : "smtp.gmail.com");
		
		Properties properties = new Properties();
		properties.put("mail.smtp.host", hostName);  
		properties.put("mail.smtp.auth", "true");  
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(properties,  
				new javax.mail.Authenticator() {  
			protected PasswordAuthentication getPasswordAuthentication() {  
				return new PasswordAuthentication(from.getUsername(), from.getPassword());  
			}  
		}); 
		
		try {  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(from.getUsername()));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(to.getUsername()));  
			message.setSubject(subject);  
			
			Multipart multipart = new MimeMultipart();
			
			
			//for the body
			BodyPart msgBody = new MimeBodyPart();
			msgBody.setText(msg);
			multipart.addBodyPart(msgBody);
			
			//for attachment
			if(!attachment.isBlank())
			{
				MimeBodyPart attch = new MimeBodyPart();
				attch.attachFile(new File(attachment));
				multipart.addBodyPart(attch);
			}
			
			message.setContent(multipart);
			Transport.send(message);
			Alert al = new Alert(AlertType.INFORMATION, "Sent!");
			al.show();
			
		} catch (MessagingException e) {
			Alert al = new Alert(AlertType.ERROR, "Invalid Entries");
			al.show();
		}
	}
	
}

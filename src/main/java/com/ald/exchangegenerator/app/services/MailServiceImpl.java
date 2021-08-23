package com.ald.exchangegenerator.app.services;

import java.util.ArrayList;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ald.exchangegenerator.app.models.domain.Contestant;

@Service
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public boolean sendMails(ArrayList<Contestant> contestants) {
		for(Contestant cont: contestants) {
			try {
				sendMail(cont);
			} catch (Exception e) {
				System.out.println("Error in sending email: " + e);
				return false;
			}
		}
		return true;
	}
	
	private void sendMail(Contestant contestant) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo(contestant.getMail());
		helper.setText("Has sido agregado a un intercambio, tu tienes que darle un regalo a "
				+ contestant.getSecretFriend().getName() + " (" + contestant.getSecretFriend().getMail() + ").");
		helper.setSubject("Intercambio de Regalos");
		
		mailSender.send(message);
	}

}

package com.ald.exchangegenerator.app.services;

import java.util.ArrayList;

import com.ald.exchangegenerator.app.models.domain.Contestant;

public interface MailService {
	public boolean sendMails(ArrayList<Contestant> contestants); 
}

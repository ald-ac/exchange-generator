package com.ald.exchangegenerator.app.services;

import java.util.List;

import com.ald.exchangegenerator.app.models.domain.Contestant;

public interface MailService {
	public boolean sendMails(List<Contestant> contestants); 
}

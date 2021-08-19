package com.ald.exchangegenerator.app.models.domain;

public class Contestant {

	private String name;
	private String mail;
	private Contestant secretFriend;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Contestant getSecretFriend() {
		return secretFriend;
	}

	public void setSecretFriend(Contestant secretFriend) {
		this.secretFriend = secretFriend;
	}

}

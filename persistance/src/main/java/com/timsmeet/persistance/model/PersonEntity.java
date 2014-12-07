package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "fo_person")
public class PersonEntity {
	
	@Id
	@GeneratedValue(generator = "personGenerator")
	@GenericGenerator(name = "personGenerator", 
		strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", 
		parameters = {@Parameter(name = "sequence_name", value = "seq_fo_person_id")})
	private long id;

	@Version
	@Column(name = "last_modification_id")
	private long lastModificationId;
	
	@Column(name = "login", nullable = false, length = 255)
	private String login;

	@Column(name = "password", nullable = false, length = 255)
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public long getLastModificationId() {
		return lastModificationId;
	}
	
}

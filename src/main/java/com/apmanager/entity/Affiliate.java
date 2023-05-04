/**
 * 
 */
package com.apmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Pedro
 * @version 1.0 28/12/2022
 * Clase que representa entidades de AFFILIATES.
 */
@Data
@Entity
@Table(name="affiliates")
public class Affiliate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id_affiliate;
	
	@Column(name="name")
	private String name;
	
	@Column(name="age")
	private Integer age;
	
	@Column(name="mail")
	private String mail;

	public Long getId_affiliate() {
		return id_affiliate;
	}

	public void setId_affiliate(Long id_affiliate) {
		this.id_affiliate = id_affiliate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Affiliate(Long id_affiliate, String name, Integer age, String mail) {
		super();
		this.id_affiliate = id_affiliate;
		this.name = name;
		this.age = age;
		this.mail = mail;
	}

	public Affiliate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

package com.xrom.ssh.service;

import java.util.List;

import com.xrom.ssh.entity.Person;

/**
 * Created by XRom
 * On 11/16/2017.11:57 PM
 */
public interface PersonService {
	Integer savePerson();

	List<Person> findAll();

	void deleteAll();
}

package com.xrom.ssh.repository.impl;

import com.xrom.ssh.entity.Person;
import com.xrom.ssh.repository.PersonRepository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XRom
 * On 11/16/2017.11:55 PM
 */
@Repository
public class PersonRepositoryImpl extends CommonRepositoryImpl<Person> implements PersonRepository {

  
}

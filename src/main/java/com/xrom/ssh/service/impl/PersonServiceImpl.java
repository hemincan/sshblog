package com.xrom.ssh.service.impl;

import java.util.List;

import com.xrom.ssh.entity.Person;
import com.xrom.ssh.repository.PersonRepository;
import com.xrom.ssh.service.PersonService;
import com.xrom.ssh.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XRom
 * On 11/16/2017.11:58 PM
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired(required = true)
    private PersonRepository personRepository;

    @Override
    public Integer savePerson() {
        Person person = new Person();
        person.setUsername("XRog");
        person.setPhone("18381005946");
        person.setAddress("chenDu");
        person.setRemark("this is XRog");
        return personRepository.save(person);
    }
    @Override
    public List<Person> findAll(){
    	return personRepository.findAll();
    }
    
    @Override
    public void deleteAll(){
//    	personRepository.findAll();
    }
    /**
     * 页数pageNum从一开始,会根据entity中不为空的字段做为条件查出
     */
    @Override
    public Page<Person> findPage(){
    	return personRepository.findPage(null, 2, 5, "");
    }
}

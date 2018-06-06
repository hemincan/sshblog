package com.xrom.ssh.controller;

import java.util.List;

import com.xrom.ssh.entity.Person;
import com.xrom.ssh.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by XRom
 * On 11/16/2017.11:59 PM
 */
@Controller
public class TestController {

    @Autowired(required=true)
    private PersonService personService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/savePerson", method = RequestMethod.GET)
    @ResponseBody
    public String savePerson() {
    	personService.savePerson();
        return "success!";
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Person> findAll(){
    	List<Person> list = personService.findAll();
//    	System.out.println(list.size());
    	return list;
    }
    @RequestMapping("/deleteAll")
    @ResponseBody
    public String deleteAll(){
    	personService.deleteAll();
    	return "success";
    }
}

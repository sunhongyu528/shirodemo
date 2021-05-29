package com.example.shirodemo;

import com.example.shirodemo.entity.Person;
import com.example.shirodemo.service.PersonRepository;
import com.example.shirodemo.utils.AdPageUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.ldap.LdapContext;
import java.util.List;

@SpringBootTest
public class LdapTest {
    @Autowired
    PersonRepository personRepository;


    @Test
    void test1(){
        List<Person> allPersons = personRepository.getAllPersons();
        System.out.println(allPersons);
    }

    @Test
    void test2() {
        Person person = personRepository.findBysAMAccountName("ceshi1");
        System.out.println(person);
    }


    @Test
    void test3() {
        boolean b = personRepository.addUser("测", "试1", "420031");
        System.out.println(b);
        List<Person> allPersons = personRepository.getAllPersons();
        System.out.println(allPersons);

    }



    @Test
    void test4() {
        Person person = personRepository.findBysAMAccountName("ceshi1");
        System.out.println(person);
        personRepository.deletePerson(person);
    }



    @Test
    void test5() {

        String userDn="cn=ceshi1,ou=demo";
        String groupDn="cn=demousers,cn=users";

        boolean c = personRepository.addUserToGroup(userDn,groupDn);
        System.out.println(c);
    }


    @Test
    void test6() {
        List<Person> allPersons = personRepository.getAllPersons();
        //System.out.println(allPersons);

        List<Person> peoples = AdPageUtils.pageBySubList(allPersons, 5, 2);

        System.out.println(peoples);
    }
    @Test
    void test7() {

        Integer count = personRepository.getCount();
        System.out.println(count);

    }



}

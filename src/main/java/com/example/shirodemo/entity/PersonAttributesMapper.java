package com.example.shirodemo.entity;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.support.LdapUtils;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;


public class PersonAttributesMapper implements AttributesMapper<Person> {

    @Override
    public Person mapFromAttributes(Attributes attrs) throws NamingException {
        Person person = new Person();
        person.setCn((String)attrs.get("cn").get());
        person.setSAMAccountName((String)attrs.get("sAMAccountName").get());
        person.setPrincipalName((String)attrs.get("userPrincipalName").get());
        person.setDisplayName((String)attrs.get("displayName").get());
        person.setGivenName((String)attrs.get("givenName").get());
        person.setMail((String)attrs.get("mail").get());
        person.setL((String)attrs.get("l").get());
        person.setName((String)attrs.get("name").get());
        person.setSn((String)attrs.get("sn").get());
        person.setSt((String)attrs.get("st").get());
        person.setTelephoneNumber((String)attrs.get("telephoneNumber").get());
        person.setUserAccountControl((String)attrs.get("userAccountControl").get());
        //person.setDn((String)attrs.get("dn").get());
        //person.setDn(LdapUtils.newLdapName(attrs.get("distinguishedName").get().toString()));


        return person;
    }
}

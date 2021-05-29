package com.example.shirodemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;
import javax.naming.Name;
import java.util.List;


@Data
@Entry(objectClasses = {"organizationalPerson","person","top"},base = "ou=Demo,dc=changhongit,dc=com")
public class Person {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private Name id;



    @Attribute(name="sAMAccountName")
    private String sAMAccountName; // xinghouliu
    @Attribute(name="userPrincipalName")
    private String principalName; // steve@example.com
    @Attribute(name="memberOf")
    private List<String> memberOf; // CN=Senior Developers,CN=Managed Service Accounts,DC=datamesh,DC=com

    @Attribute(name = "cn")
    private String cn;

    @Attribute(name = "displayName")
    private String displayName;

    @Attribute(name = "givenName")
    private String givenName;

    @Attribute(name = "l")
    private String l;

    @Attribute(name = "mail")
    private String mail;

    @Attribute(name = "name")
    private String name;

    @Attribute(name = "sn")
    private String sn;

    @Attribute(name = "st")
    private String st;

    @Attribute(name = "telephoneNumber")
    private String telephoneNumber;

    @Attribute(name = "userAccountControl")
    protected String userAccountControl;


    @Attribute(name = "dn")
    protected String dn;

}
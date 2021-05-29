package com.example.shirodemo.service;

import com.example.shirodemo.entity.Person;
import com.example.shirodemo.entity.PersonAttributesMapper;
import com.example.shirodemo.utils.PinYinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.ldap.LdapName;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by zhoujm on 2020/2/20/020.
 */
@Service
public class PersonRepository {
    @Autowired
    private LdapTemplate ldapTemplate;


    public List<String> getAllPersonNames() {
        return ldapTemplate.search(
                query().where("objectclass").is("person"), (AttributesMapper<String>) attrs -> (String) attrs.get("cn").get());
    }




    public Person findBysAMAccountName(String sAMAccountName){
//        String filter = "(&(objectCategory=Person)(userPrincipalName="+userPrincipalName+"))";
        return ldapTemplate.findOne(query().base("ou=Demo").where("sAMAccountName").is(sAMAccountName), Person.class);
    }


    public Person modifyPerson(Person person){
        ldapTemplate.update(person);
        return person;
    }

    public void deletePerson(Person person){
        ldapTemplate.delete(person);
    }

    // List所有用户
    public List<Person> getAllPersons() {
        return ldapTemplate.search(query()
                .base("ou=Demo")
                .where("objectclass").is("person"), new PersonAttributesMapper());
    }


    public Integer getCount(){
        List<Person> list = ldapTemplate.search(query()
                .base("ou=Demo")
                .where("objectclass").is("person"), new PersonAttributesMapper());
        return list.size();
    }


    public boolean addUser(String xing1,  String ming1,String telephoneNumber) {

        String xing=PinYinUtil.getPinyin(xing1);
        String ming=PinYinUtil.getPinyin(ming1);

        String sAMAccountName=xing+ming;
        String l="beijing";
        String st="beijing";

        Attributes attrs = new BasicAttributes();
        BasicAttribute oc = new BasicAttribute("objectClass");
        oc.add("top");
        oc.add("person");
        oc.add("organizationalPerson");
        oc.add("user");
        attrs.put(oc);
        attrs.put("sAMAccountName", sAMAccountName);
        attrs.put("cn", sAMAccountName);
        attrs.put("displayName", xing1+ming1);
        attrs.put("l", l);
        attrs.put("st", st);
        attrs.put("name", xing1+ming1);
        attrs.put("givenName", xing1);
        attrs.put("telephoneNumber", telephoneNumber);
        attrs.put("sn", ming1);
        attrs.put("userAccountControl", "66048");
        attrs.put("mail", sAMAccountName+"@changhongit.com");
        attrs.put("userPrincipalName", sAMAccountName+"@changhongit.com");

        String pwd = "Avaya123"; // 初始密码

        String baseDn="ou=Demo";
        attrs.put("unicodePwd", encodePwd(pwd));

        LdapName dn = LdapNameBuilder.newInstance(baseDn)
                .add("cn", sAMAccountName)
                .build();
        ldapTemplate.bind(dn, null, attrs);
        return true;
    }

    private byte[] encodePwd(String source){
        String quotedPassword = "\"" + source + "\""; // 注意：必须在密码前后加上双引号
        return quotedPassword.getBytes(StandardCharsets.UTF_16LE);
    }

// 使用：addUser("张三","san.zhang","ou=部门一,ou=测试公司")



    public Person findPersonWithDn(String dn) {
        return ldapTemplate.lookup(dn, new PersonAttributesMapper());
    }


    public boolean addUserToGroup(String userDn,String groupDn) {

        DirContextOperations ctxGroup = ldapTemplate.lookupContext(groupDn);
        DirContextOperations ctxUser = ldapTemplate.lookupContext(userDn);
        ctxGroup.addAttributeValue("member", ctxUser.getStringAttribute("distinguishedName"));
        ldapTemplate.modifyAttributes(ctxGroup);
        return true;
    }
// 使用： addUserToGroup("cn=小明,ou=部门一,ou=测试公司","cn=分组一,ou=测试公司")

}

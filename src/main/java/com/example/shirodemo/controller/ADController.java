package com.example.shirodemo.controller;

import com.example.shirodemo.entity.Person;
import com.example.shirodemo.service.AdService;
import com.example.shirodemo.service.PersonRepository;
import com.example.shirodemo.utils.PinYinUtil;
import com.example.shirodemo.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad")
public class ADController {
    @Autowired
    private AdService adService;
    @Autowired
    PersonRepository personRepository;
    @RequestMapping("/list")
    public DataVO list(){
        return adService.findlist();
    }


    @RequestMapping("/pagelist")
    public DataVO list(Integer page,Integer limit){
        return adService.pageFindlist(page,limit);
    }

    @RequestMapping("/addperson")
    public DataVO addPerson(String xing,String ming,String phone){

        boolean b = personRepository.addUser(xing,ming,phone);
        System.out.println(b);
        String xing1= PinYinUtil.getPinyin(xing);
        String ming1=PinYinUtil.getPinyin(ming);
        String userDn="cn="+xing1+ming1+",ou=demo";
        String groupDn="cn=demousers,cn=users";
        boolean c = personRepository.addUserToGroup(userDn,groupDn);
        System.out.println(c);
        List<Person> allPersons = personRepository.getAllPersons();
        System.out.println(allPersons);

        return null;
    }


    @RequestMapping("/delperson")
    public DataVO delPerson(@RequestBody Map<String, String> map){
        String sAMAccountName=map.get("samaccountName");
        Person person = personRepository.findBysAMAccountName(sAMAccountName);
        System.out.println(person);
        personRepository.deletePerson(person);

        return null;
    }
}

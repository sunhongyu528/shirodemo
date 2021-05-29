package com.example.shirodemo.service.impl;

import com.example.shirodemo.entity.Person;
import com.example.shirodemo.service.AdService;
import com.example.shirodemo.service.PersonRepository;
import com.example.shirodemo.utils.AdPageUtils;
import com.example.shirodemo.vo.DataADPersonVO;
import com.example.shirodemo.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public DataVO<DataADPersonVO> findlist() {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        dataVO.setCount(personRepository.getCount());


        List<Person> personList=personRepository.getAllPersons();
        List<DataADPersonVO> dataADPersonList=new ArrayList<>();
        for (Person person:personList){
            DataADPersonVO dataADPersonVO=new DataADPersonVO();
            dataADPersonVO.setId(Integer.valueOf(person.getTelephoneNumber()));
            dataADPersonVO.setDisplayName(person.getDisplayName());
            dataADPersonVO.setSAMAccountName(person.getSAMAccountName());
            dataADPersonVO.setSn(person.getSn());
            dataADPersonVO.setGivenName(person.getGivenName());
            dataADPersonVO.setTelephoneNumber(person.getTelephoneNumber());
            dataADPersonVO.setMail(person.getMail());

            dataADPersonList.add(dataADPersonVO);

        }
        dataVO.setData(dataADPersonList);
        return dataVO;
    }

    @Override
    public DataVO<DataADPersonVO> pageFindlist(Integer page, Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        dataVO.setCount(personRepository.getCount());

        List<Person> allPersons = personRepository.getAllPersons();
        List<Person> personList = AdPageUtils.pageBySubList(allPersons, limit, page);
        List<DataADPersonVO> dataADPersonList=new ArrayList<>();
        for (Person person:personList){
            DataADPersonVO dataADPersonVO=new DataADPersonVO();
            dataADPersonVO.setId(Integer.valueOf(person.getTelephoneNumber()));
            dataADPersonVO.setDisplayName(person.getDisplayName());
            dataADPersonVO.setSAMAccountName(person.getSAMAccountName());
            dataADPersonVO.setSn(person.getSn());
            dataADPersonVO.setGivenName(person.getGivenName());
            dataADPersonVO.setTelephoneNumber(person.getTelephoneNumber());
            dataADPersonVO.setMail(person.getMail());

            dataADPersonList.add(dataADPersonVO);

        }
        dataVO.setData(dataADPersonList);
        return dataVO;
    }


}




package com.example.shirodemo.service;

import com.example.shirodemo.vo.DataADPersonVO;
import com.example.shirodemo.vo.DataVO;

public interface AdService {

    public DataVO<DataADPersonVO> findlist();

    public DataVO<DataADPersonVO> pageFindlist(Integer page,Integer limit);

}

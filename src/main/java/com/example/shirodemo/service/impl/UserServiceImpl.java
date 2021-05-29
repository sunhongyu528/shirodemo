package com.example.shirodemo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.shirodemo.entity.User;
import com.example.shirodemo.mapper.UserMapper;
import com.example.shirodemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shirodemo.utils.UserPageUtils;
import com.example.shirodemo.vo.DataUserVO;
import com.example.shirodemo.vo.DataVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孙宏宇
 * @since 2021-01-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public DataVO<DataUserVO> findData(Integer page, Integer limit) {
        DataVO dataVo = new DataVO();
        dataVo.setCode(0);
        dataVo.setMsg("");
        dataVo.setCount(userMapper.selectCount(null));
        List<User> allusers = userMapper.selectList(null);
        List<User> userList = UserPageUtils.pageBySubList(allusers, limit, page);

        List<DataUserVO> userVoList=new ArrayList<>();
        for (User user:userList){
            DataUserVO userVo=new DataUserVO();
            BeanUtils.copyProperties(user,userVo);
            userVoList.add(userVo);

        }

        dataVo.setData(userVoList);

        return dataVo;
    }
}

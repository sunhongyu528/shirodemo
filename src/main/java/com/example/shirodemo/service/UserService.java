package com.example.shirodemo.service;

import com.example.shirodemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.shirodemo.vo.DataUserVO;
import com.example.shirodemo.vo.DataVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孙宏宇
 * @since 2021-01-25
 */
public interface UserService extends IService<User> {

    public DataVO<DataUserVO> findData(Integer page, Integer limit);

}

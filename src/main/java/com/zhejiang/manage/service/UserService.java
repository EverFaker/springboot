package com.zhejiang.manage.service;

import com.zhejiang.manage.model.Criteria;
import com.zhejiang.manage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    int countByParams(Criteria example);

    User selectByPrimaryKey(Integer id);

    List<User> selectByParams(Criteria example);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int deleteByParams(Criteria example);

    int updateByParamsSelective(User record, Criteria example);

    int updateByParams(User record, Criteria example);

    int insert(User record);

    int insertSelective(User record);
}
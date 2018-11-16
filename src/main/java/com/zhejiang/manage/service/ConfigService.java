package com.zhejiang.manage.service;

import com.zhejiang.manage.model.Config;
import com.zhejiang.manage.model.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigService {
    int countByParams(Criteria example);

    Config selectByPrimaryKey(Integer id);

    List<Config> selectByParams(Criteria example);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

    int deleteByParams(Criteria example);

    int updateByParamsSelective(Config record, Criteria example);

    int updateByParams(Config record, Criteria example);

    int insert(Config record);

    int insertSelective(Config record);
}
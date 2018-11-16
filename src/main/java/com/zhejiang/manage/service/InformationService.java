package com.zhejiang.manage.service;

import com.zhejiang.manage.model.Criteria;
import com.zhejiang.manage.model.Information;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InformationService {
    int countByParams(Criteria example);

    Information selectByPrimaryKey(Integer id);

    List<Information> selectByParams(Criteria example);

    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    int deleteByParams(Criteria example);

    int updateByParamsSelective(Information record, Criteria example);

    int updateByParams(Information record, Criteria example);

    int insert(Information record);

    int insertSelective(Information record);
}